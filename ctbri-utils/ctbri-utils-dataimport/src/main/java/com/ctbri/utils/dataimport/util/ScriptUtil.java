package com.ctbri.utils.dataimport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.ctbri.common.utils.StringUtils;
import com.ctbri.utils.dataimport.core.DataTemplate;

/**
 * 脚本操作
 * 
 * @author Hogan
 *
 */
public class ScriptUtil {

	private static Logger log = Logger.getLogger(ScriptUtil.class);

	private static DataTemplate dataTemplate = new DataTemplate();
	private static final String NOTE_STR = "--";
	private static final String END_FALG = ";";

	/**
	 * 执行脚本
	 * 
	 * @param scriptPath
	 */
	public static void exceuteScript(String scriptName) {
		StringBuilder sb = new StringBuilder();
		InputStream in = ScriptUtil.class.getClassLoader().getResourceAsStream(scriptName);
		BufferedReader reader = null;
		try {
			log.info("开始执行脚本!");
			String line;
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((line = reader.readLine()) != null) {
				if (!line.contains(NOTE_STR) && !StringUtils.isNullOrBlank(line)) {
					if (line.endsWith(END_FALG)) {
						sb.append(line);
						dataTemplate.excute(sb.toString());
						sb.delete(0, sb.length());
					} else {
						sb.append(line);
						sb.append(" ");
					}
				} else {
					log.info(line);
				}
			}
			log.info("执行脚本完毕!");
		} catch (Exception e) {
			log.error("文件加载失败!", e);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				log.error("文件关闭失败!", e);
			}
		}
	}
}
