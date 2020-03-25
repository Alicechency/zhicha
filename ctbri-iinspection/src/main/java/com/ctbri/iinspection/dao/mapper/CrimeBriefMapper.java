package com.ctbri.iinspection.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.ctbri.iinspection.pojo.CrimeBrief;

/**
 * 案情简要信息查询类
 * 
 * @author wkhuahuo
 *
 */
public interface CrimeBriefMapper {

	/**
	 * 根据id查询案情的简要信息
	 * 
	 * @param crimeId
	 * @return
	 */
	public CrimeBrief findCrimebriefById(@Param("crimeId") String crimeId);

}
