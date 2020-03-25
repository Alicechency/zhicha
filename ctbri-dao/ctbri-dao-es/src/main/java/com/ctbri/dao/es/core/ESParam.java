package com.ctbri.dao.es.core;

import java.util.ArrayList;
import java.util.List;

/**
 * ES查询参数
 * 
 * @author Hogan
 *
 */
public class ESParam {

	/**
	 * 查询类型
	 */
	private List<QType> qTypes = new ArrayList<QType>();
	/**
	 * 查询条件的属性名
	 */
	private List<String> keys = new ArrayList<String>();
	/**
	 * 查询条件的属性值
	 */
	private List<Object> values = new ArrayList<Object>();

	public List<QType> getqTypes() {
		return qTypes;
	}

	public void setqTypes(List<QType> qTypes) {
		this.qTypes = qTypes;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

}
