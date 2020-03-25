package com.ctbri.dsearch.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.common.utils.StringUtils;
import com.ctbri.dsearch.dao.neo4j.GraphDao;
import com.ctbri.dsearch.service.SearchService;

/**
 * 搜索业务层实现
 * 
 * @author Hogan
 *
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Resource
	private GraphDao graphDao;

	@Override
	public CJSONObject simpleSearch(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String word = jParams.getString("word");
		if (!StringUtils.isNullOrBlank(word)) {
			Map<String, Double> relationWords = graphDao.findRelationWords(word, 30);
		}
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
