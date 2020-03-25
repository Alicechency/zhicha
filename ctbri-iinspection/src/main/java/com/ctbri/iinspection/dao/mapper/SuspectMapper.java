package com.ctbri.iinspection.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctbri.iinspection.pojo.Suspect;

/**
 * 嫌疑人数据层接口
 * 
 * @author Hogan
 *
 */
public interface SuspectMapper {

	/***
	 * 读取所有的嫌疑人记录数
	 * 
	 * @return
	 */
	public int countSuspects();

	/**
	 * 通过预警级别查询记录数
	 * 
	 * @param warningLevel
	 * @return
	 */
	public int countSuspectsByWarningLevel(@Param(value = "warningLevel") int warningLevel);

	/**
	 * 查询所有嫌疑人信息
	 * 
	 * @return
	 */
	public List<Suspect> findAllSuspects();

	/**
	 * 通过预警级别查询嫌疑人信息
	 * 
	 * @param warningLevel
	 * @return
	 */
	public List<Suspect> findSuspectsByWarningLevel(@Param(value = "warningLevel") int warningLevel);

	/**
	 * 通过嫌疑人姓名查询信息
	 * 
	 * @param importantPersonName
	 * @return
	 */
	public List<Suspect> findSuspectsByName(@Param(value = "importantPersonName") String importantPersonName);

	/**
	 * 通过id获取嫌疑人的详情信息
	 * 
	 * @param suspectId
	 * @return
	 */
	public Suspect findSuspectDetailById(@Param(value = "suspectId") Integer suspectId);

	/**
	 * 通过嫌疑人编号查询嫌疑人信息
	 * 
	 * @param importantPersonNos
	 * @return
	 */
	public List<Suspect> findSuspectsByIds(@Param("importantPersonNos") String[] importantPersonNos);

	/**
	 * 通过重要人员ID查询嫌疑人信息
	 * 
	 * @param importantPersonNo
	 * @return
	 */
	public Suspect findSuspectByImportantPersonNo(@Param("importantPersonNo") String importantPersonNo);

}
