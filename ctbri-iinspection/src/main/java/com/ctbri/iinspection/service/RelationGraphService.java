package com.ctbri.iinspection.service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;

/**
 * 关联业务层接口
 * 
 * @author Hogan
 *
 */
public interface RelationGraphService {

	/**
	 * 加载关联人员信息
	 * 
	 * @param jParams
	 * @return
	 */
	public CJSONObject loadSuspectRelationship(JSONObject jParams);

}
