package com.ctbri.iinspection.service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;

/**
 * 嫌疑人业务层接口
 * 
 * @author Hogan
 *
 */
public interface SuspectService {

	/**
	 * 加载各种嫌疑人的数目
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadAllSuspectCounts(JSONObject jParams);

	/**
	 * 加载所有犯罪信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadAllSuspects(JSONObject jParams);

	/**
	 * 加载特定级别嫌疑人
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadWarningLevelSuspects(JSONObject jParams);

	/**
	 * 查询嫌疑人信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject searchSuspects(JSONObject jParams);

	/**
	 * 加载嫌疑人详情
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadSuspectDetail(JSONObject jParams);

	/**
	 * 查询相关嫌疑人信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadSuspectRelationCases(JSONObject jParams);

	/**
	 * 加载嫌疑人基本信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadSuspectBaseInfo(JSONObject jParams);

}
