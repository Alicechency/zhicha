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
import com.ctbri.iinspection.service.MapService;

/**
 * 地图控制层实现
 * 
 * @author Hogan
 *
 */
@Controller
@RequestMapping("/map")
public class MapController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private MapService mapService;

	/**
	 * 加载案情关联图
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadCaseRelationShip")
	public @ResponseBody JSONObject loadCaseRelationShip(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = mapService.loadCaseRelationShip(rt.getJParams());
		} catch (Exception e) {
			log.error("加载案情关联图失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

	/**
	 * 加载外来人口信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadOutsiders")
	public @ResponseBody JSONObject loadOutsiders(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = mapService.loadOutsiders(rt.getJParams());
		} catch (Exception e) {
			log.error("加载外来人口信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	/**
	 * 根据时间加载犯罪预测信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadPredictionByDate")
	public @ResponseBody JSONObject loadPredictionByDate(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = mapService.loadPredictionByDate(rt.getJParams());
		} catch (Exception e) {
			log.error("根据时间加载犯罪预测信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
