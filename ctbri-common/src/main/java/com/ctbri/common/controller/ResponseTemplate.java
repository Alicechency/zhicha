package com.ctbri.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.common.utils.Consts;

/**
 * 响应模板
 * 
 * @author Hogan
 * 
 */
public class ResponseTemplate {

	private CJSONObject detail;

	public ResponseTemplate() {
	}

	public ResponseTemplate(CJSONObject detail) {
		this.detail = detail;
	}

	/**
	 * 获取返回的Json数据
	 * 
	 * @return
	 */
	public JSONObject getReturn() {
		if (detail == null) {
			detail = new CJSONObject();
			detail.setResult(Consts.RESULT_FAILD);
		} else {
			if (ErrorCode.SUCCESS.getDescription().equals(detail.getString(Consts.LABEL_ERROR_CODE))) {
				detail.setResult(Consts.RESULT_SUCCESS);
			} else {
				detail.setResult(Consts.RESULT_FAILD);
			}
		}
		return detail;
	}

}
