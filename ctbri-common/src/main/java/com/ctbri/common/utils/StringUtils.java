package com.ctbri.common.utils;

/**
 * 字符串处理工具类
 * 
 * @author Hogan
 *
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
}
