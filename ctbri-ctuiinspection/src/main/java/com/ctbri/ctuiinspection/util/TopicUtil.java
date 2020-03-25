package com.ctbri.ctuiinspection.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by shaohui on 17-7-14.
 */
public class TopicUtil {

	private static Map<Integer, String[]> topicWord = new HashMap<Integer, String[]>();
	private static Map<Integer, Double> topicScoreRank = new TreeMap<Integer, Double>();
	private static JiebaSegmenter segmenter;

	static {
		InputStream in = TopicUtil.class.getClassLoader().getResourceAsStream(Consts.PATH_TOPIC_WORD);
		segmenter = new JiebaSegmenter();
		BufferedReader reader = null;
		// 主题-词矩阵
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempString = null;
			int line = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				topicWord.put(line, tempString.split(" "));
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 生成query对应主题词列表
	 * 
	 * @param query
	 * @return
	 */
	public static List<String> generateTopicWords(String query) {
		topicScore(query);
		List<String> result = new ArrayList<>();
		Set<String> flag = new HashSet<>();
		int i = 0;
		// 将文档主题向量中得分最高的主题选前两个主题词作为标签，得分次高的主题选第一个主题词作为标签
		for (Integer key : topicScoreRank.keySet()) {
			if (i == 0) {
				if (!flag.contains(topicWord.get(key)[0])) {
					result.add(topicWord.get(key)[0]);
					flag.add(topicWord.get(key)[0]);
				}
				if (!flag.contains(topicWord.get(key)[1])) {
					result.add(topicWord.get(key)[1]);
					flag.add(topicWord.get(key)[1]);
				}
			} else if (i == 1) {
				if (!flag.contains(topicWord.get(key)[0])) {
					result.add(topicWord.get(key)[0]);
					flag.add(topicWord.get(key)[0]);
				}
			} else {
				break;
			}
			i++;
		}
		return result;
	}

	/**
	 * 主题打分
	 * 
	 * @param query
	 * @return
	 */
	private static Map<Integer, Double> topicScore(String query) {
		Map<Integer, Double> topicScore = new TreeMap<Integer, Double>();
		List<SegToken> words = segmenter.process(query, JiebaSegmenter.SegMode.INDEX);
		for (int i = 0; i < topicWord.size(); i++) {
			topicScore.put(i, 0.0);
		}
		int i;
		// 生成主题向量 每个词所属主题+1
		for (SegToken word : words) {
			i = 0;
			for (String[] everyTopic : topicWord.values()) {
				for (String keyword : everyTopic) {
					if (keyword.equals(word.word)) {
						topicScore.put(i, topicScore.get(i) + 1.0);
						break;
					}
				}
				i += 1;
			}

		}
		Double sum = 0.0;
		for (Integer key : topicScore.keySet()) {
			sum += topicScore.get(key);
		}
		// 归一化
		for (Integer key : topicScore.keySet()) {
			topicScore.put(key, (topicScore.get(key) + 1e-20) / (sum + 1e-20 * topicScore.size()));
		}
		Map<Integer, Double> tempTopicScoreRank = new TreeMap<Integer, Double>(new Comparator<Integer>() {
			public int compare(Integer obj1, Integer obj2) {
				if (topicScore.get(obj1) > topicScore.get(obj2)) {
					return -1;
				} else if (topicScore.get(obj1) < topicScore.get(obj2)) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		tempTopicScoreRank.putAll(topicScore);
		topicScoreRank = tempTopicScoreRank;
		return topicScoreRank;
	}

	private static Map<Integer, Double> similar(Map<Integer, Double> topicScoreRank) {
		Map<Integer, Double> topicScore = new HashMap<Integer, Double>();
		topicScore.putAll(topicScoreRank);
		Map<Integer, Double[]> topicVector = new HashMap<Integer, Double[]>();
		InputStream in = TopicUtil.class.getClassLoader().getResourceAsStream(Consts.PATH_TEXT_TOPIC);
		BufferedReader reader = null;
		// 主题-词矩阵
		try {
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempString = null;
			int line = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				String[] arr = tempString.split(" ");
				Double[] tempDouble = new Double[arr.length];
				double distance = 0.0;
				for (int i = 0; i < arr.length; i++) {
					tempDouble[i] = Double.parseDouble(arr[i]);
					distance += Math.pow(tempDouble[i], 2);
				}
				distance = Math.sqrt(distance);
				for (int i = 0; i < tempDouble.length; i++) {
					tempDouble[i] /= distance;
				}
				topicVector.put(line, tempDouble);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		// 文档主题矩阵
		Matrix documentTopicMatrix = DenseMatrix.Factory.zeros(topicVector.size(), topicScore.size());
		// 要比较的主题向量，列向量
		Matrix documentTopicVector = DenseMatrix.Factory.zeros(topicScore.size(), 1);
		for (int i = 0; i < topicVector.size(); i++) {
			for (int j = 0; j < topicScore.size(); j++) {
				documentTopicMatrix.setAsDouble(topicVector.get(i)[j], i, j);
			}
		}
		double distance = 0.0;
		for (int i = 0; i < topicScore.size(); i++) {
			if (topicScore.containsKey(i)) {
				distance += Math.pow(topicScore.get(i), 2);
			}
		}
		distance = Math.sqrt(distance);
		for (int i = 0; i < topicScore.size(); i++) {
			if (topicScore.containsKey(i)) {
				documentTopicVector.setAsDouble(topicScore.get(i) / distance, i, 0);
			}
		}
		Matrix similar = documentTopicMatrix.mtimes(documentTopicVector);
		Map<Integer, Double> similarMap = new TreeMap<Integer, Double>();
		for (int i = 0; i < similar.getSize(0); i++) {
			similarMap.put(i, similar.getAsDouble(i, 0));
		}
		Map<Integer, Double> similarRank = new TreeMap<Integer, Double>(new Comparator<Integer>() {
			public int compare(Integer obj1, Integer obj2) {

				if (similarMap.get(obj1) > similarMap.get(obj2)) {
					return -1;
				} else if (similarMap.get(obj1) < similarMap.get(obj2)) {
					return 1;
				} else {
					return 0;
				}

			}
		});
		similarRank.putAll(similarMap);
		return similarRank;
	}

	private static Map<Integer, Double> similarText(String query) {
		Map<Integer, Double> topicword = topicScore(query);
		final Map<Integer, Double> similar = similar(topicword);
		Map<Integer, Double> similar100 = new TreeMap<Integer, Double>(new Comparator<Integer>() {
			public int compare(Integer obj1, Integer obj2) {
				if (similar.get(obj1) > similar.get(obj2)) {
					return -1;
				} else if (similar.get(obj1) < similar.get(obj2)) {
					return 1;
				} else {
					return 0;
				}

			}
		});
		int i = 0;
		for (Integer key : similar.keySet()) {
			if (i < 100) {
				similar100.put(key, similar.get(key));
			} else {
				break;
			}
			i++;
		}
		return similar100;
	}

	/**
	 * 
	 * @return
	 */
	public static List<Integer> generateSimilarIds(String query) {
		List<Integer> result = new ArrayList<>();
		Map<Integer, Double> topicScore = similarText(query);
		for (Integer key : topicScore.keySet()) {
			result.add(key);
		}
		return result;
	}

}