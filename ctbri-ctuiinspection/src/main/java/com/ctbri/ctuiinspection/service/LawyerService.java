package com.ctbri.ctuiinspection.service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;

/**
 * 律师业务层接口
 * 
 * @author Hogan
 *
 */
public interface LawyerService {

	/**
	 * 加载推荐律师
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadRecommendedLawyer(JSONObject jParams);

	/**
	 * 加载关系图
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadRelationGraph(JSONObject jParams);

	/**
	 * 加载律师雷达图
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadLawyerRadar(JSONObject jParams);

}
