package com.ctbri.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.utils.Consts;

/**
 * 请求JSON模板
 * 
 * @author Hogan
 * 
 */
public class RequestTemplate {

	/**
	 * 请求参数全部封装到params中
	 */
	public JSONObject params = null;

	public RequestTemplate(JSONObject reqJson) {
		if (reqJson != null) {
			params = reqJson.getJSONObject(Consts.LABEL_PARAMS);
		}
	}

	/**
	 * 获取JSON参数集
	 * 
	 * @return
	 */
	public JSONObject getJParams() {
		return params;
	}

}
