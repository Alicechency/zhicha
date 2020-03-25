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
import com.ctbri.iinspection.service.SuspectService;

/**
 * 嫌疑人控制层实现
 * 
 * @author wkhuahuo
 *
 */
@Controller
@RequestMapping("/suspect")
public class SuspectController {
	
	private Logger log = Logger.getLogger(getClass());

	@Resource
	private SuspectService suspectService;
	
	/**
	 * 加载各种嫌疑人的数目
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadAllSuspectCounts")
	public @ResponseBody JSONObject loadSuspectCounts(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.loadAllSuspectCounts(rt.getJParams());
		} catch (Exception e) {
			log.error("加载各种嫌疑人的数目失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 加载所有犯罪信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadAllSuspects")
	public @ResponseBody JSONObject loadAllSuspects(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.loadAllSuspects(rt.getJParams());
		} catch (Exception e) {
			log.error("加载所有犯罪信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();

	}
	
	/**
	 * 加载特定级别嫌疑人
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadWarningLevelSuspects")
	public @ResponseBody JSONObject loadWarningLevelSuspects(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.loadWarningLevelSuspects(rt.getJParams());
		} catch (Exception e) {
			log.error("加载特定级别嫌疑人失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 查询嫌疑人信息
	 *
	 * @param jo
	 * @return
	 */
	@RequestMapping("searchSuspects")
	public @ResponseBody JSONObject searchSuspects(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.searchSuspects(rt.getJParams());
		} catch (Exception e) {
			log.error("查询嫌疑人信息败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 加载嫌疑人详情
	 *
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSuspectDetail")
	public @ResponseBody JSONObject loadSuspectDetail(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.loadSuspectDetail(rt.getJParams());
		} catch (Exception e) {
			log.error("加载嫌疑人详情失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 查询相关嫌疑人信息
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSuspectRelationCases")
	public @ResponseBody JSONObject loadSuspectRelationCases(@RequestBody JSONObject jo){
		CJSONObject detail =null;
		try{
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.loadSuspectRelationCases(rt.getJParams());
		}catch (Exception e) {
			log.error("查询相关嫌疑人信息失败！",e);
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 加载嫌疑人基本信息
	 *
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSuspectBaseInfo")
	public @ResponseBody JSONObject loadSuspectRelationshipInfo(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = suspectService.loadSuspectBaseInfo(rt.getJParams());
		} catch (Exception e) {
			log.error("加载嫌疑人信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
