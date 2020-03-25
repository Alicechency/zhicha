package com.ctbri.ctuiinspection.controller;

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
import com.ctbri.ctuiinspection.service.CaseService;

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
	 * 加载关键词
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadKeyword")
	public @ResponseBody JSONObject loadKeyword(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadKeyword(rt.getJParams());
		} catch (Exception e) {
			log.error("加载关键词失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载同类案件
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSimilarCase")
	public @ResponseBody JSONObject loadSimilarCase(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.loadSimilarCase(rt.getJParams());
		} catch (Exception e) {
			log.error("加载同类案件失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 得到同类案件的结果
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("generateSimilarIds")
	public @ResponseBody JSONObject generateSimilarIds(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.generateSimilarIds(rt.getJParams());
		} catch (Exception e) {
			log.error("加载同类案件结果失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 获取案件关系图
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("generateCaseRelationship")
	public @ResponseBody JSONObject generateCaseRelationship(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = caseService.generateCaseRelationship(rt.getJParams());
		} catch (Exception e) {
			log.error("获取案件关系图失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
}
