package com.ctbri.ctuiinspection.util;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.dao.mybatis.page.PageInfo;
import com.ctbri.common.utils.Consts;

/**
 * 组合分页返回结果
 * 
 * @author Hogan
 */
public class PageJsonUtil {

	/**
	 * 组合分页返回结果
	 * 
	 * @param pageInfo
	 * @return
	 */
	public static JSONObject generatePageJson(PageInfo<?> pageInfo) {
		JSONObject page = new JSONObject();
		page.put(Consts.PAGE_NUM, pageInfo.getPageNum());
		page.put(Consts.PAGE_HAVE_NEXT, pageInfo.isHasNextPage());
		page.put(Consts.PAGE_SUM, pageInfo.getPages());
		page.put(Consts.PAGE_RECORD_COUNT, pageInfo.getTotal());
		return page;
	}
}
