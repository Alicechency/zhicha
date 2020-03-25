package com.ctbri.iinspection.dao.mapper;

import java.util.List;
import java.util.Map;

import com.ctbri.iinspection.pojo.Outsider;

/**
 * 外来人口数据层接口
 * 
 * @author YYR
 *
 */
public interface OutsiderMapper {

	/**
	 * 通过时间,经纬度查询外来人口信息
	 * 
	 * @param date
	 * @return
	 */
	public List<Outsider> findOutsiderByDate(Map<String, Object> map);
}
