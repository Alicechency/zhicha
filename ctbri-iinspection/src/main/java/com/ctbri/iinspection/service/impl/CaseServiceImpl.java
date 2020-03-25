package com.ctbri.iinspection.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.page.PageResult;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.iinspection.dao.es.CaseDao;
import com.ctbri.iinspection.dao.mapper.CaseTagMapper;
import com.ctbri.iinspection.dao.mapper.CrimeBriefMapper;
import com.ctbri.iinspection.dao.mapper.CrimeNearCaseMapper;
import com.ctbri.iinspection.pojo.Case;
import com.ctbri.iinspection.pojo.CaseTag;
import com.ctbri.iinspection.pojo.CrimeBrief;
import com.ctbri.iinspection.pojo.CrimeNearCase;
import com.ctbri.iinspection.service.CaseService;
import com.ctbri.iinspection.util.Consts;

/**
 * 案情业务层实现
 * 
 * @author Hogan
 *
 */
@Service("caseService")
public class CaseServiceImpl implements CaseService {

	@Resource
	private CaseDao caseDao;
	@Resource
	private CaseTagMapper caseTagMapper;
	@Resource
	private CrimeBriefMapper crimeBriefMapper;
	@Resource
	private CrimeNearCaseMapper crimeNearCaseMapper;

	@Override
	public CJSONObject loadCaseCounts(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		long caseCount = caseDao.findCaseCount();
		long levelOneCaseCount = caseDao.findCountByOneParam("categoryname", Consts.CASE_LEVEL_ONE);
		long levelTwoCaseCount = caseDao.findCountByOneParam("categoryname", Consts.CASE_LEVEL_TWO);
		long levelThreeCaseCount = caseDao.findCountByOneParam("categoryname", Consts.CASE_LEVEL_THREE);
		long levelFourCaseCount = caseDao.findCountByOneParam("categoryname", Consts.CASE_LEVEL_FOUR);
		detail.put("caseCount", caseCount);
		detail.put("levelOneCaseCount", levelOneCaseCount);
		detail.put("levelTwoCaseCount", levelTwoCaseCount);
		detail.put("levelThreeCaseCount", levelThreeCaseCount);
		detail.put("levelFourCaseCount", levelFourCaseCount);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	
	@Override
	public CJSONObject loadTags(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		List<CaseTag> tags = caseTagMapper.findAllTags();
		detail.put("tags", tags);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}


	@Override
	public CJSONObject loadAllCases(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		PageResult<Case> pageResult = new PageResult<Case>();
		pageResult.setPerPage(Consts.PAGE_SIZE_CASE);
		pageResult.setCurrentPage(pageNum);
		int startCount = (pageResult.getCurrentPage() - 1) * Consts.PAGE_SIZE_CASE;
		List<Case> cases = caseDao.findCasesByPage(startCount, Consts.PAGE_SIZE_CASE);
		pageResult.setRecords(cases);
		pageResult.setTotal(caseDao.findCaseCount());
		detail.put("pageResult", pageResult);
		result.setErrorCode(ErrorCode.SUCCESS);
		result.setDetail(detail);
		return result;
	}


	@Override
	public CJSONObject loadLevelCases(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		int pageNum = jParams.getInteger("pageNum");
		int level = jParams.getInteger("level");
		PageResult<Case> pageResult = new PageResult<Case>();
		pageResult.setPerPage(Consts.PAGE_SIZE_CASE);
		pageResult.setCurrentPage(pageNum);
		int startCount = (pageResult.getCurrentPage() - 1) * Consts.PAGE_SIZE_CASE;
		List<Case> cases = caseDao.findCasesByOneParam(startCount, Consts.PAGE_SIZE_CASE, "categoryname", level);
		pageResult.setRecords(cases);
		pageResult.setTotal(caseDao.findCountByOneParam("categoryname", level));
		detail.put("pageResult", pageResult);
		result.setErrorCode(ErrorCode.SUCCESS);
		result.setDetail(detail);
		return result;
	}


	@Override
	public CJSONObject loadTagCases(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		int pageNum = jParams.getInteger("pageNum");
		String tagName = jParams.getString("tagName");
		PageResult<Case> pageResult = new PageResult<Case>();
		pageResult.setPerPage(Consts.PAGE_SIZE_CASE);
		pageResult.setCurrentPage(pageNum);
		int startCount = (pageResult.getCurrentPage() - 1) * Consts.PAGE_SIZE_CASE;
		List<Case> cases = caseDao.findCasesByMultiParams(startCount, Consts.PAGE_SIZE_CASE, "tagname", tagName);
		pageResult.setRecords(cases);
		pageResult.setTotal(caseDao.findCountByMultiParams("tagname", tagName));
		detail.put("pageResult", pageResult);
		result.setErrorCode(ErrorCode.SUCCESS);
		result.setDetail(detail);
		return result;
	}


	@Override
	public CJSONObject searchCases(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		int pageNum = jParams.getInteger("pageNum");
		String word = jParams.getString("word");
		PageResult<Case> casePage = new PageResult<Case>();
		casePage.setPerPage(Consts.PAGE_SIZE_CASE);
		casePage.setCurrentPage(pageNum);
		int startCount = (casePage.getCurrentPage() - 1) * Consts.PAGE_SIZE_CASE;
		List<Case> cases = caseDao.findCaseByFuzzy(startCount, Consts.PAGE_SIZE_CASE, "contents", word);
		casePage.setRecords(cases);
		casePage.setTotal(caseDao.findCountByFuzzy("contents",word));
		detail.put("pageResult", casePage);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}


	@Override
	public CJSONObject loadCaseDetail(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String caseId = jParams.getString("caseId");
		Case cas = caseDao.findCaseById(caseId);
		detail.put("cas", cas);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}


	@Override
	public CJSONObject loadCaseBrief(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String caseId = jParams.getString("caseId");
		CrimeBrief crimeBrief = crimeBriefMapper.findCrimebriefById(caseId);
		detail.put("cas", crimeBrief);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}


	@Override
	public CJSONObject loadCaseChart(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String crimeId = jParams.getString("caseId");
		List<CrimeNearCase> caseChart = crimeNearCaseMapper.findCrimeNearCaseById(crimeId);
		detail.put("caseChart", caseChart);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}
	
}
