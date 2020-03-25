package com.ctbri.iinspection.service;

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
	 * 加载各种案情记录数
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadCaseCounts(JSONObject jParams);

	/**
	 * 加载所有标签
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadTags(JSONObject jParams);

	/**
	 * 加载所有案情
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadAllCases(JSONObject jParams);

	/**
	 * 加载等级案情
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadLevelCases(JSONObject jParams);

	/**
	 * 加载标签案件
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadTagCases(JSONObject jParams);

	/**
	 * 查询案情信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject searchCases(JSONObject jParams);

	/**
	 * 加载案情详情
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadCaseDetail(JSONObject jParams);

	/**
	 * 加载案情简要信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadCaseBrief(JSONObject jParams);

	/**
	 * 加载案情时间图表信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadCaseChart(JSONObject jParams);

}
