package com.ctbri.common.dao;

/**
 * 查询回调函数
 * 
 * @author Hogan
 *
 * @param <T>
 * @param <B>
 */
public interface SearchCallback<T, B> {

	/**
	 * 实地查询
	 * 
	 * @param b
	 * @return
	 */
	public T doInSearch(B b);
}
