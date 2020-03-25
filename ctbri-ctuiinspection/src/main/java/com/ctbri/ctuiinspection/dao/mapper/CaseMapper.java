package com.ctbri.ctuiinspection.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctbri.ctuiinspection.pojo.Case;

/**
 * 案件数据层接口
 * 
 * @author Hogan
 *
 */
public interface CaseMapper {

	/**
	 * 根据ID集合获取案件信息
	 * 
	 * @param caseIds
	 * @return
	 */
	public List<Case> findCasesByIds(List<Integer> caseIds);

	public String findCaseTitleByCaseId(@Param("caseId") Integer caseId);

}
