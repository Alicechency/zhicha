package com.ctbri.ctuiinspection.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.ctuiinspection.service.CaseService;
import com.ctbri.ctuiinspection.test.base.BaseTest;

public class TestCaseService extends BaseTest {

	@Resource
	private CaseService caseService;

	@Test
	public void testGenerateCaseRelationship() {
		JSONObject jParams = new JSONObject();
		Integer caseId = 11;
		jParams.put("caseId", caseId);
		CJSONObject result = caseService.generateCaseRelationship(jParams);
		System.out.println(result);
	}
}
