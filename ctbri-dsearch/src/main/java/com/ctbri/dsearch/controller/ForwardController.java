package com.ctbri.dsearch.controller;

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
//@RequestMapping("/forward")
public class ForwardController {

	public static final String PATH_INDEX = "/index";
	public static final String PATH_SEARCH = "/search";

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping("/forward/index")
	public String forwardIndex() {
		return PATH_INDEX;
	}

	/**
	 * 跳转到查询页面
	 * 
	 * @return
	 */
	@RequestMapping("/forward/search")
	public String forwardIndex(HttpServletRequest request, String word) {
		request.setAttribute("word", word);
		return PATH_SEARCH;
	}

}
