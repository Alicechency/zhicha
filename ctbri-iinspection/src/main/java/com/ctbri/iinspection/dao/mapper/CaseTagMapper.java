package com.ctbri.iinspection.dao.mapper;

import java.util.List;

import com.ctbri.iinspection.pojo.CaseTag;

/**
 * 标签数据层接口
 * 
 * @author Hogan
 *
 */
public interface CaseTagMapper {

	/**
	 * 查询所有标签
	 * 
	 * @return
	 */
	public List<CaseTag> findAllTags();
}
