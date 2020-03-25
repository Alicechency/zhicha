package com.ctbri.iinspection.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctbri.iinspection.pojo.CrimeNearCase;

/**
 * 犯罪相关案情数据层接口
 * 
 * @author Hogan
 *
 */
public interface CrimeNearCaseMapper {

	/**
	 * 根据crimeId查询一组信息
	 * 
	 * @param crimeId
	 * @return
	 */
	public List<CrimeNearCase> findCrimeNearCaseById(@Param("crimeId") String crimeId);
}
