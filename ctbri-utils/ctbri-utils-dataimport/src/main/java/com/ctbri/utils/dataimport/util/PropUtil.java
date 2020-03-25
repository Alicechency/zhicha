package com.ctbri.utils.dataimport.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件工具类
 * 
 * @author Hogan
 *
 */
public class PropUtil {

	private static Logger log = Logger.getLogger(PropUtil.class);

	private static Properties prop = new Properties();

	static {
		try {
			prop = new Properties();
			prop.load(new InputStreamReader(
					PropUtil.class.getClassLoader().getResourceAsStream(Consts.CONFIG_FILE_NAME), "UTF-8"));
		} catch (IOException e) {
			log.error("加载配置为失败!");
		}
	}

	/**
	 * 通过key获取value
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return (String) prop.get(key);
	}

}
