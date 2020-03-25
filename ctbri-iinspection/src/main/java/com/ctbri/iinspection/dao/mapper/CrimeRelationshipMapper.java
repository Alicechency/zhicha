package com.ctbri.iinspection.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctbri.iinspection.pojo.CrimeRelationship;

/**
 * 案情关系数据层接口
 * 
 * @author Hogan
 *
 */
public interface CrimeRelationshipMapper {

	/**
	 * 通过案件ID查询
	 * 
	 * @param crimeId
	 * @return
	 */
	public List<CrimeRelationship> findByCrimeId(@Param("crimeId") Long crimeId);
}
