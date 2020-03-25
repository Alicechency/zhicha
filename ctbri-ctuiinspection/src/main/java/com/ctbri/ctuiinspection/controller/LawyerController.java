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
import com.ctbri.ctuiinspection.service.LawyerService;

/**
 * 律师控制层实现
 * 
 * @author Hogan
 * 
 */
@Controller
@RequestMapping("/lawyer")
public class LawyerController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private LawyerService lawyerService;
	
	/**
	 * 加载关系图
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadRelationGraph")
	public @ResponseBody JSONObject loadRelationGraph(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = lawyerService.loadRelationGraph(rt.getJParams());
		} catch (Exception e) {
			log.error("加载关系图失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 加载推荐律师
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadRecommendedLawyer")
	public @ResponseBody JSONObject loadRecommendedLawyer(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = lawyerService.loadRecommendedLawyer(rt.getJParams());
		} catch (Exception e) {
			log.error("加载推荐律师失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 加载律师雷达图
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadLawyerRadar")
	public @ResponseBody JSONObject loadLawyerRadar(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = lawyerService.loadLawyerRadar(rt.getJParams());
		} catch (Exception e) {
			log.error("加载律师雷达图失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
