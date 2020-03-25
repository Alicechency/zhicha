package com.ctbri.iinspection.dao.mapper;

import java.util.List;
import java.util.Map;

import com.ctbri.iinspection.pojo.CrimePrediction;

public interface CrimePredictionMapper {

	/**
	 * 通过时间查询信息
	 * 
	 * @param date
	 * @return
	 */
	public List<CrimePrediction> findCrimePredictionByDate(Map<String, Object> map);
}
