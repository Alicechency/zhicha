package com.ctbri.ctuiinspection.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.ctuiinspection.service.LawyerService;
import com.ctbri.ctuiinspection.test.base.BaseTest;

public class TestLawyerService extends BaseTest {

	@Resource
	private LawyerService lawyerService;

	@Test
	public void TestloadRecommendedLawyer() {
		JSONObject jParams = new JSONObject();
		Integer[] caseIds = new Integer[] { 25659 };
		jParams.put("case_id", caseIds);
		jParams.put("page_num", 1);
		CJSONObject result = lawyerService.loadRecommendedLawyer(jParams);
		System.out.println(result);
	}

	@Test
	public void testLoadLawyerRadar() {
		JSONObject jParams = new JSONObject();
		Integer[] lawyerIds = new Integer[] { 1 };
		jParams.put("lawyer_ids", lawyerIds);
		CJSONObject result = lawyerService.loadLawyerRadar(jParams);
		System.out.println(result);
	}

}
