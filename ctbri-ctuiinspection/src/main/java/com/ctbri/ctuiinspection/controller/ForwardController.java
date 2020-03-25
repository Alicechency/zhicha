package com.ctbri.ctuiinspection.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 资源跳转类
 * 
 * @author Hogan
 *
 */
@Controller
@RequestMapping("/forward")
public class ForwardController {

	private static final String PATH_INDEX = "/index";
	public static final String PATH_SEARCH_INFO = "/search_info";

	private String index = PATH_INDEX;
	private String searchIndex = PATH_SEARCH_INFO;

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String forwardIndex() {
		return index;
	}

	/**
	 * 跳转到搜索结果页面
	 * 
	 * @return
	 */
	@RequestMapping("searchInfo")
	public String forwardSearchInfo(HttpServletRequest request, String searchWord) {
		request.setAttribute("searchWord", searchWord);
		return searchIndex;
	}

}
