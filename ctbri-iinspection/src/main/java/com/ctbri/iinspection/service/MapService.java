package com.ctbri.iinspection.service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;

/**
 * 地图业务层接口
 * 
 * @author Hogan
 *
 */
public interface MapService {

	/**
	 * 加载案情关联图
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadCaseRelationShip(JSONObject jParams);

	/**
	 * 加载外来人口信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadOutsiders(JSONObject jParams);
	
	/**
	 * 根据日期加载犯罪预测信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadPredictionByDate(JSONObject jParams);
	

}
