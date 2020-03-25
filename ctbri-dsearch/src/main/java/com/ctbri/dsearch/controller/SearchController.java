package com.ctbri.dsearch.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.controller.RequestTemplate;
import com.ctbri.common.controller.ResponseTemplate;
import com.ctbri.dsearch.dao.neo4j.GraphDao;
import com.ctbri.dsearch.dao.neo4j.impl.GraphDaoImpl;
import com.ctbri.dsearch.service.SearchService;

/**
 * 搜索模块控制层实现
 * 
 * @author Hogan
 *
 */
@Controller
@Scope
public class SearchController {

	private Logger log = Logger.getLogger(getClass());

	@Resource
	private SearchService searchService;

	/**
	 * 简单查询(单词查询)
	 * 
	 * @param jo
	 * @return
	 */
	@RequestMapping("simpleSearch")
	public @ResponseBody JSONObject simpleSearch(@RequestBody JSONObject jo) {
		CJSONObject detail = null;
		try {
			RequestTemplate rt = new RequestTemplate(jo);
			detail = searchService.simpleSearch(rt.getJParams());
		} catch (Exception e) {
			log.error("简单查询失败!", e);
			return new ResponseTemplate().getReturn();
		}
		return new ResponseTemplate(detail).getReturn();
	}

}
