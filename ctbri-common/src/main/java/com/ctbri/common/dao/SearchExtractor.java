package com.ctbri.common.dao;

/**
 * 自定义封装搜索结果
 * 
 * @author Hogan
 *
 * @param <T>
 * @param <B>
 */
public interface SearchExtractor<T, B> {

	/**
	 * 榨取搜索结果
	 * 
	 * @param b
	 * @return
	 */
	public T extractData(B b);
}
