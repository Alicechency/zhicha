package com.ctbri.ctuiinspection.dao.neo4j;

import java.util.List;

import com.ctbri.ctuiinspection.service.impl.LawyerServiceImpl.LawyerRelationship;

/**
 * 案件关系数据层接口
 * 
 * @author Hogan
 *
 */
public interface CaseRelationDao {

	public List<List<LawyerRelationship>> findRelationCaseByName(Integer caseId);
	
}
