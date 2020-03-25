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
import com.ctbri.iinspection.service.RelationGraphService;

/**
 * 关联控制层实现
 * 
 * @author Hogan
 *
 */
@Controller
@RequestMapping("/relation")
public class RelationGraphController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private RelationGraphService relationGraphService;

	/**
	 * 加载关联人员信息
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("loadSuspectRelationship")
	public @ResponseBody JSONObject loadSuspectRelationship(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = relationGraphService.loadSuspectRelationship(rt.getJParams());
		} catch (Exception e) {
			log.error("加载关联人员信息失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}
	
	
}
