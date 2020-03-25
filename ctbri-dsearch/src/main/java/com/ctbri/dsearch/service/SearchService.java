package com.ctbri.dsearch.service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;

/**
 * 搜索业务层接口
 * 
 * @author Hogan
 *
 */
public interface SearchService {

	/**
	 * 简单查询
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject simpleSearch(JSONObject jParams);

}
