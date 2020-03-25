package com.ctbri.ctuiinspection.dao.neo4j;

import com.ctbri.ctuiinspection.service.impl.LawyerServiceImpl.LawyerRelationship;

/**
 * 律师关系数据层接口
 * 
 * @author Hogan
 *
 */
public interface LawyerRelationDao {

	public LawyerRelationship findRelationLawyerByName(String lawyerName);
}
