package com.ctbri.iinspection.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.ctbri.common.controller.CJSONObject;
import com.ctbri.common.type.ErrorCode;
import com.ctbri.iinspection.dao.mapper.CrimePredictionMapper;
import com.ctbri.iinspection.dao.mapper.CrimeRelationshipMapper;
import com.ctbri.iinspection.dao.mapper.OutsiderMapper;
import com.ctbri.iinspection.pojo.CrimePrediction;
import com.ctbri.iinspection.pojo.CrimeRelationship;
import com.ctbri.iinspection.pojo.Outsider;
import com.ctbri.iinspection.service.MapService;
import com.ctbri.iinspection.service.bean.CrimeRelationships;

/**
 * 地图业务层实现
 * 
 * @author Hogan
 *
 */
@Service("mapService")
public class MapServiceImpl implements MapService {

	@Resource
	private CrimeRelationshipMapper crimeRelationshipMapper;
	
	@Resource
	private CrimePredictionMapper crimePredictionMapper;
	
	@Resource
    private OutsiderMapper outsiderMapper;

	@Override
	public CJSONObject loadCaseRelationShip(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Long crimeId = jParams.getLong("id");
		List<CrimeRelationship> crimeRelationship = crimeRelationshipMapper.findByCrimeId(crimeId);
		CrimeRelationships crimeRelationships = new CrimeRelationships();
		if (crimeRelationship != null && crimeRelationship.size() > 0) {
			crimeRelationships.setId(crimeRelationship.get(0).getCrimeId());
			for (CrimeRelationship e : crimeRelationship) {
				switch (e.getCaseType()) {
				case 1:
					crimeRelationships.getNearCaseValues().add(e.getCaseValue());
					crimeRelationships.getNearCaseDistances().add(e.getDistance());
					crimeRelationships.getNearCaseIds().add(e.getNearCrimeId());
					break;
				//case2是嫌疑人
				case 2:
					crimeRelationships.getSuspectValues().add(e.getCaseValue());
					crimeRelationships.getSuspectDistances().add(e.getDistance());
					break;
				//case3是事主
				case 3:
					crimeRelationships.setIdentity(e.getCaseValue());
					break;
				default:
					break;
				}
			}
		}
		detail.put("crimeRelationships", crimeRelationships);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

	@Override
	public CJSONObject loadOutsiders(JSONObject jParams) {
	    CJSONObject result = new CJSONObject();
        JSONObject detail = new JSONObject();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", jParams.getString("dateStr"));
        map.put("leftlatitude", jParams.getDouble("leftlatitude"));
        map.put("leftlongitude", jParams.getDouble("leftlongitude"));
        map.put("rightlatitude", jParams.getDouble("rightlatitude"));
        map.put("rightlongitude", jParams.getDouble("rightlongitude"));
        List<Outsider> outsiders = outsiderMapper.findOutsiderByDate(map);
        detail.put("outsiders", outsiders);
        result.setDetail(detail);
        result.setErrorCode(ErrorCode.SUCCESS);
        return result;
	}

	@Override
	public CJSONObject loadPredictionByDate(JSONObject jParams) {
		CJSONObject result = new CJSONObject();
		JSONObject detail = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", jParams.getString("timeStr"));
        map.put("category", jParams.getString("category"));
        List<CrimePrediction> crimePredictions = crimePredictionMapper.findCrimePredictionByDate(map);
		detail.put("crimePredictions", crimePredictions);
		result.setDetail(detail);
		result.setErrorCode(ErrorCode.SUCCESS);
		return result;
	}

}
