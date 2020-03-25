package com.ctbri.ctuiinspection.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctbri.ctuiinspection.pojo.Lawyer;

/**
 * 律师数据层接口
 * 
 * @author lijunpu
 *
 */
public interface LawyerMapper {

	/**
	 * 根据ID集合获取案件信息
	 * 
	 * @param caseIds
	 * @return
	 */
	public List<Lawyer> findRecommendedLawyerByCaseIds(Integer[] caseIds);

	public List<Lawyer> findWinPercentageByLawyerName(@Param("lawyerName") String lawyerName);

	public Lawyer findLawyerById(@Param("lawyerId") Integer lawyerId);

}
