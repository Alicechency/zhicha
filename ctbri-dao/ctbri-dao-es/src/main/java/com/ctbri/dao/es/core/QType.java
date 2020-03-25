package com.ctbri.dao.es.core;

/**
 * ES查询类型
 * 
 * @author Hogan
 *
 */

public enum QType {

	/**
	 * 单条件精确查询
	 */
	ONE_PARAM,
	/**
	 * 多值查询
	 */
	MUTIL_PARAM,
	/**
	 * 模糊查询
	 */
	FUZZY_PARAM,
	;
	
}