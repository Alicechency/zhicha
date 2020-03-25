package com.ctbri.ctuiinspection.service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;

/**
 * 案情业务接口
 * 
 * @author Hogan
 *
 */
public interface CaseService {

	/**
	 * 加载关键词
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadKeyword(JSONObject jParams);

	/**
	 * 加载同类案件
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadSimilarCase(JSONObject jParams);

	/**
	 * 得到同类案件的结果
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject generateSimilarIds(JSONObject jParams);

	/**
	 * 获取案件关系图
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject generateCaseRelationship(JSONObject jParams);

}
