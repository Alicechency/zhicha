package com.ctbri.iinspection.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.dao.mybatis.page.PageHelper;
import com.ctbri.dao.mybatis.page.PageInfo;
import com.ctbri.iinspection.dao.mapper.SuspectMapper;
import com.ctbri.iinspection.pojo.Suspect;
import com.ctbri.iinspection.service.SuspectService;
import com.ctbri.iinspection.service.bean.SuspectServiceBean;
import com.ctbri.iinspection.type.WarningLevel;
import com.ctbri.iinspection.util.Consts;

/**
 * 嫌疑人业务实现
 * 
 * @author Hogan
 *
 */
@Service("suspectService")
public class SuspectServiceImpl implements SuspectService {

	@Resource
	private SuspectMapper suspectMapper;

	@Override
	public CJSONObject loadAllSuspectCounts(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		long suspectCount = suspectMapper.countSuspects();
		long suspectCountBlue = suspectMapper.countSuspectsByWarningLevel(WarningLevel.BULE.getId());
		long suspectCountYellow = suspectMapper.countSuspectsByWarningLevel(WarningLevel.YELLOW.getId());
		long suspectCountRed = suspectMapper.countSuspectsByWarningLevel(WarningLevel.RED.getId());
		long suspectCountOrange = suspectMapper.countSuspectsByWarningLevel(WarningLevel.ORANGE.getId());
		detail.put("suspectCount", suspectCount);
		detail.put("suspectCountBlue", suspectCountBlue);
		detail.put("suspectCountYellow", suspectCountYellow);
		detail.put("suspectCountRed", suspectCountRed);
		detail.put("suspectCountOrange", suspectCountOrange);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadAllSuspects(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		PageHelper.startPage(pageNum, Consts.PAGE_SIZE_CASE);
		List<Suspect> suspects = suspectMapper.findAllSuspects();
		PageInfo<Suspect> pageInfo = new PageInfo<Suspect>(suspects);
		detail.put("pageResult", pageInfo);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadWarningLevelSuspects(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		Integer warningLevel = jParams.getInteger("warningLevel");
		PageHelper.startPage(pageNum, Consts.PAGE_SIZE_CASE);
		List<Suspect> suspects = suspectMapper.findSuspectsByWarningLevel(warningLevel);
		PageInfo<Suspect> pageInfo = new PageInfo<Suspect>(suspects);
		detail.put("pageResult", pageInfo);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject searchSuspects(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		int pageNum = jParams.getInteger("pageNum");
		String word = jParams.getString("word");
		PageHelper.startPage(pageNum, Consts.PAGE_SIZE_CASE);
		List<Suspect> suspects = suspectMapper.findSuspectsByName(word);
		PageInfo<Suspect> pageInfo = new PageInfo<Suspect>(suspects);
		detail.put("pageResult", pageInfo);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		result.setDetail(detail);
		return result;
	}

	@Override
	public CJSONObject loadSuspectDetail(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer suspectId = jParams.getInteger("suspectId");
		Suspect suspectDetail = suspectMapper.findSuspectDetailById(suspectId);
		SuspectServiceBean suspectServiceBean = new SuspectServiceBean();
		suspectServiceBean.setSuspect(suspectDetail);
		detail.put("suspectDetail", suspectServiceBean);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadSuspectRelationCases(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Integer pageNum = jParams.getInteger("pageNum");
		String[] suspectIds = jParams.getObject("suspectIds", String[].class);
		String[] importantPersonIds = new String[Consts.PAGE_SIZE_CASE];
		for (int i = 0; i < Consts.PAGE_SIZE_CASE && i < suspectIds.length; i++) {
			importantPersonIds[i] = suspectIds[(pageNum - 1) * Consts.PAGE_SIZE_CASE + i];
		}
		PageHelper.startPage(pageNum, Consts.PAGE_SIZE_CASE);
		List<Suspect> suspects = suspectMapper.findSuspectsByIds(importantPersonIds);
		PageInfo<Suspect> pageInfo = new PageInfo<Suspect>(suspects);
		detail.put("pageResult", pageInfo);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadSuspectBaseInfo(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		String importantPersonNo = jParams.getString("id");
		Suspect suspect = suspectMapper.findSuspectByImportantPersonNo(importantPersonNo);
		detail.put("suspect", suspect);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
