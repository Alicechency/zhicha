package com.ctbri.iinspection.dao.mapper;

import java.util.List;
import java.util.Map;

import com.ctbri.iinspection.pojo.SupervisoryAssociation;

/**
 * 嫌疑人关系数据层接口
 * 
 * @author Hogan
 *
 */
public interface SupervisoryAssociationMapper {

	/**
	 * 通过起始点查询
	 * 
	 * @param map
	 * @return
	 */
	public List<SupervisoryAssociation> findByStartWord(Map<String, Object> map);

	/**
	 * 通过起始点查询，并过滤特定结束点
	 * 
	 * @param map
	 * @return
	 */
	public List<SupervisoryAssociation> findByStartWordAndExcludeEndWord(Map<String, Object> map);
}
