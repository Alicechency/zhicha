package com.ctbri.iinspection.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.controller.RequestTemplate;
import com.ctbri.common.controller.ResponseTemplate;
import com.ctbri.iinspection.service.CaseService;

/**
 * 案情控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/case")
public class CaseController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private CaseService caseService;

	/**
	 * 加载各种案情记录数
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCaseCounts")
	public @ResponseBody JSONObject loadCaseCounts(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadCaseCounts(rt.getJParams());
		} catch (Exception e) {
			log.error("加载各种案情记录数失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载所有标签
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadTags")
	public @ResponseBody JSONObject loadTags(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadTags(rt.getJParams());
		} catch (Exception e) {
			log.error("加载所有标签失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载所有案情
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadAllCases")
	public @ResponseBody JSONObject loadAllCases(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadAllCases(rt.getJParams());
		} catch (Exception e) {
			log.error("加载所有案情失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载等级案情
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadLevelCases")
	public @ResponseBody JSONObject loadLevelCases(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadLevelCases(rt.getJParams());
		} catch (Exception e) {
			log.error("加载等级案情失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载标签案件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadTagCases")
	public @ResponseBody JSONObject loadTagCases(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadTagCases(rt.getJParams());
		} catch (Exception e) {
			log.error("加载标签案件失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 查询案情信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("searchCases")
	public @ResponseBody JSONObject searchCases(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.searchCases(rt.getJParams());
		} catch (Exception e) {
			log.error("查询案情信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载案情详情
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCaseDetail")
	public @ResponseBody JSONObject loadCaseDetail(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadCaseDetail(rt.getJParams());
		} catch (Exception e) {
			log.error("加载案情详情失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载案情简要信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCaseBrief")
	public @ResponseBody JSONObject loadCaseBrief(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadCaseBrief(rt.getJParams());
		} catch (Exception e) {
			log.error("查询案情简介信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 加载案情时间图表信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCaseChart")
	public @ResponseBody JSONObject loadCaseChart(@RequestBody JSONObject jo){
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadCaseChart(rt.getJParams());
		} catch (Exception e) {
			log.error("加载案情时间图表信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
