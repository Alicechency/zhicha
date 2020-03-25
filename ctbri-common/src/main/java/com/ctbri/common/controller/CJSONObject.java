package com.ctbri.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.type.ErrorType;
import com.ctbri.common.utils.Consts;

/**
 * 在原有JSONObject的基础上做一些适合项目的加工
 * 
 * @author Hogan
 * 
 */
public class CJSONObject extends JSONObject {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认操作是成功的
	 */
	public CJSONObject() {
		setResult(Consts.RESULT_SUCCESS);
	}

	/**
	 * 设置错误信息
	 * 
	 * @param errorCode
	 *            错误码
	 * @return
	 */
	public Object setErrorCode(ErrorType errorCode) {
		return put(Consts.LABEL_ERROR_CODE, errorCode.getDescription());
	}

	/**
	 * 设置结果类型
	 * 
	 * @param result
	 *            结果码
	 * @return
	 */
	public Object setResult(String result) {
		return put(Consts.LABEL_RESULT, result);
	}

	/**
	 * 设置业务操作结果
	 * 
	 * @param detail
	 *            业务处理结果
	 * @return
	 */
	public Object setDetail(JSONObject detail) {
		return put(Consts.LABEL_DETAIL, detail);
	}

	public Object setPager(JSONObject pager) {
		return put("page", pager);
	}

}
