package com.ctbri.iinspection.controller;

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

	public static final String PATH_INDEX = "/index";
	public static final String PATH_GRAPH = "/graph";
	public static final String PATH_MAP = "/map";
	public static final String PATH_OPINIONS = "/opinions";
	
	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String forwardIndex() {
		return PATH_INDEX;
	}

	/**
	 * 跳转到关系图谱页面
	 * 
	 * @param word
	 * @return
	 */
	@RequestMapping("graph")
	public String forwardGraph(HttpServletRequest request, String word) {
		request.setAttribute("word", word);
		return PATH_GRAPH;
	}
	
	/**
	 * 跳转到地图页面
	 * 
	 * @return
	 */
	@RequestMapping("map")
	public String forwardMap() {
		return PATH_MAP;
	}
	
	/**
	 * 跳转到热力地图页面
	 * 
	 * @param request
	 * @param word
	 * @param layer
	 * @return
	 */
	@RequestMapping("heatmap")
	public String forwardHeatmap(HttpServletRequest request, String word, String layer) {
		request.setAttribute("word", word);
		request.setAttribute("layer", layer);
		return PATH_MAP;
	}
	
	/**
	 * 跳转到舆情页面
	 * 
	 * @return
	 */
	@RequestMapping("opinions")
	public String forwardOpinions() {
		return PATH_OPINIONS;
	}
	
}
