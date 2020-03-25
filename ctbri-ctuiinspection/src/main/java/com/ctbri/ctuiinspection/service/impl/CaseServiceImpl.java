package com.ctbri.ctuiinspection.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.page.PageResult;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.ctuiinspection.dao.mapper.CaseMapper;
import com.ctbri.ctuiinspection.dao.neo4j.CaseRelationDao;
import com.ctbri.ctuiinspection.pojo.Case;
import com.ctbri.ctuiinspection.service.CaseService;
import com.ctbri.ctuiinspection.service.impl.LawyerServiceImpl.LawyerRelationship;
import com.ctbri.ctuiinspection.type.JudgeResult;
import com.ctbri.ctuiinspection.type.RelationCategory;
import com.ctbri.ctuiinspection.util.Consts;
import com.ctbri.ctuiinspection.util.GlobalContext;
import com.ctbri.ctuiinspection.util.TopicUtil;

/**
 * 案情业务层实现
 * 
 * @author Hogan
 *
 */
@Service("caseService")
public class CaseServiceImpl implements CaseService {

	@Resource
	private CaseMapper caseMapper;
	@Resource
	private CaseRelationDao caseRelationDao;
	@Resource
	private GlobalContext globalContext;

	@Override
	public CJSONObject loadKeyword(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String searchWord = jParams.getString("search_word");
		List<String> keywords = TopicUtil.generateTopicWords(searchWord);
		detail.put("keywords", keywords);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadSimilarCase(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String searchWord = jParams.getString("search_word");
		Integer pageNum = jParams.getInteger("page_num");
		List<Integer> topicScore = null;
		if (globalContext.getSearchWordMap().containsKey(searchWord)) {
			topicScore = globalContext.getSearchWordMap().get(searchWord);
		} else {
			topicScore = TopicUtil.generateSimilarIds(searchWord);
			globalContext.getSearchWordMap().put(searchWord, topicScore);
		}
		List<Integer> caseIds = new ArrayList<>();
		for (int i = Consts.PAGE_SIZE_CASE * Math.max(pageNum - 1, 0), j = 0; i < topicScore.size()
				&& j < Consts.PAGE_SIZE_CASE; i++, j++) {
			caseIds.add(topicScore.get(i));
		}
		PageResult<Case> page = new PageResult<>();
		page.setCurrentPage(pageNum);
		page.setPerPage(Consts.PAGE_SIZE_CASE);
		page.setTotal(topicScore.size());
		List<Case> cases = caseMapper.findCasesByIds(caseIds);
		detail.put("cases", cases);
		result.setDetail(detail);
		result.put("page", page);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject generateSimilarIds(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String searchWord = jParams.getString("search_word");
		List<Integer> topicScore = null;
		if (globalContext.getSearchWordMap().containsKey(searchWord)) {
			topicScore = globalContext.getSearchWordMap().get(searchWord);
		} else {
			topicScore = TopicUtil.generateSimilarIds(searchWord);
			globalContext.getSearchWordMap().put(searchWord, topicScore);
		}
		List<Integer> caseIds = new ArrayList<>();
		for (int i = 0; i < topicScore.size(); i++) {
			caseIds.add(topicScore.get(i));
		}
		List<Case> cases = caseMapper.findCasesByIds(caseIds);
		int[] nums = { 0, 0, 0, 0 };
		for (int i = 0; i < cases.size(); i++) {
			switch (JudgeResult.byId(cases.get(i).getJudgeResult())) {
			case PLAINTIFF_SUCCESS:
				nums[0]++;
				break;
			case DEFENDENT_SUCCESS:
				nums[1]++;
				break;
			case WITHDRAWAL:
				nums[2]++;
				break;
			case REITERATE:
				nums[3]++;
				break;
			default:
				break;
			}
		}
		detail.put("nums", nums);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject generateCaseRelationship(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer caseId = jParams.getInteger("caseId");
		List<List<LawyerRelationship>> caseRealtionship = caseRelationDao.findRelationCaseByName(caseId);
		Map<String, String> map = new HashMap<>();
		for (List<LawyerRelationship> e : caseRealtionship) {
			for (LawyerRelationship e1 : e) {
				if (e1.getCategory().equals(RelationCategory.CASE.getDescription())) {
					if (map.containsKey(e1.getId())) {
						e1.setName(map.get(e1.getId()));
					} else {
						String name = caseMapper.findCaseTitleByCaseId(Integer.parseInt(e1.getId()));
						e1.setName(name);
						map.put(e1.getId(), name);
					}
				}
			}
		}
		detail.put("caseRealtionship", caseRealtionship);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
