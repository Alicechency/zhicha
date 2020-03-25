package com.ctbri.ctuiinspection.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.ctuiinspection.dao.mapper.LawyerMapper;
import com.ctbri.ctuiinspection.dao.mapper.TopicMapper;
import com.ctbri.ctuiinspection.dao.neo4j.LawyerRelationDao;
import com.ctbri.ctuiinspection.pojo.Lawyer;
import com.ctbri.ctuiinspection.pojo.Topic;
import com.ctbri.ctuiinspection.service.LawyerService;
import com.ctbri.ctuiinspection.util.Consts;
import com.ctbri.ctuiinspection.util.PageJsonUtil;
import com.ctbri.dao.mybatis.page.PageHelper;
import com.ctbri.dao.mybatis.page.PageInfo;

/**
 * 律师业务层实现
 * 
 * @author Hogan
 *
 */
@Service("lawyerService")
public class LawyerServiceImpl implements LawyerService {

	@Resource
	private LawyerMapper lawyerMapper;

	@Resource
	private LawyerRelationDao lawyerRelationDao;

	@Resource
	private TopicMapper topicMapper;

	@Override
	public CJSONObject loadRecommendedLawyer(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer[] caseIds = jParams.getObject("case_id", Integer[].class);
		Integer pageNum = jParams.getInteger("page_num");
		PageHelper.startPage(pageNum, Consts.PAGE_SIZE_CASE);
		List<Lawyer> lawyers = lawyerMapper.findRecommendedLawyerByCaseIds(caseIds);
		for (Lawyer e : lawyers) {
			e.setTopic(generateTopicStr(topicMapper.findTopicsByLawyerName(e.getName())));
			e.setWinningPercentage(genereateWinPercentage(lawyerMapper.findWinPercentageByLawyerName(e.getName())));
		}
		PageInfo<Lawyer> pageInfo = new PageInfo<>(lawyers);
		detail.put("lawyers", pageInfo.getList());
		result.setDetail(detail);
		result.setPager(PageJsonUtil.generatePageJson(pageInfo));
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadRelationGraph(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String name = jParams.getString("name");
		LawyerRelationship lawyerRelationship = lawyerRelationDao.findRelationLawyerByName(name);
		detail.put("lawyerRelationship", lawyerRelationship);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadLawyerRadar(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer[] lawyerIds = jParams.getObject("lawyer_ids", Integer[].class);
		List<LawyerRadar> lawyerRadars = new ArrayList<>();
		for (Integer lawyerId : lawyerIds) {
			Lawyer lawyer = lawyerMapper.findLawyerById(lawyerId);
			List<String> topics = topicMapper.findTopicByLawyerId(lawyerId);
			LawyerRadar lawyerRadar = new LawyerRadar();
			lawyerRadar.setId(lawyer.getLawyerId());
			lawyerRadar.setLawyerName(lawyer.getName());
			List<String> targetTopics = new ArrayList<>();
			List<Integer> targetNums = new ArrayList<>();
			Map<String, Integer> tempTopics = new HashMap<>();
			for (String topic : topics) {
				if (!tempTopics.containsKey(topic)) {
					tempTopics.put(topic, 1);
				} else {
					tempTopics.put(topic, tempTopics.get(topic) + 1);
				}
			}
			for (String key : tempTopics.keySet()) {
				targetTopics.add(key);
				targetNums.add(tempTopics.get(key));
			}
			lawyerRadar.setCaseNames(targetTopics);
			lawyerRadar.setNums(targetNums);
			lawyerRadars.add(lawyerRadar);
		}
		detail.put("lawyerRadars", lawyerRadars);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	private String generateTopicStr(List<Topic> topics) {
		StringBuilder result = new StringBuilder();
		for (Topic e : topics) {
			result.append(e.getTopicName());
			result.append("|");
		}
		return result.toString().substring(0, result.toString().length() - 1);
	}

	private int genereateWinPercentage(List<Lawyer> winPercentages) {
		int sum = winPercentages.size();
		int count = 0;
		for (Lawyer e : winPercentages) {
			if (e.getType() == 0 && e.getJudgeResult() == 0) {
				count++;
			} else if (e.getType() == 1 && e.getJudgeResult() == 1) {
				count++;
			}
		}
		return count * 100 / sum;
	}

	public static class LawyerRelationship {

		private String id;
		private String name;
		private String category;
		private List<LawyerRelationship> sons;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public List<LawyerRelationship> getSons() {
			return sons;
		}

		public void setSons(List<LawyerRelationship> sons) {
			this.sons = sons;
		}

	}

	public static class LawyerRadar {

		private Integer id;
		private String lawyerName;
		private List<String> caseNames;
		private List<Integer> nums;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getLawyerName() {
			return lawyerName;
		}

		public void setLawyerName(String lawyerName) {
			this.lawyerName = lawyerName;
		}

		public List<String> getCaseNames() {
			return caseNames;
		}

		public void setCaseNames(List<String> caseNames) {
			this.caseNames = caseNames;
		}

		public List<Integer> getNums() {
			return nums;
		}

		public void setNums(List<Integer> nums) {
			this.nums = nums;
		}

	}

}
