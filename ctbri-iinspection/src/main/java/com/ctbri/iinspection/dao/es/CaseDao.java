package com.ctbri.iinspection.dao.es;

import java.util.List;

import com.ctbri.iinspection.pojo.Case;

/**
 * 案情数据层接口
 * 
 * @author Hogan
 * 
 */
public interface CaseDao {

	/**
	 * 无条件分页查询案情信息
	 * 
	 * @param startCount
	 * @param CaseSize
	 * @return
	 */
	public List<Case> findCasesByPage(int startCount, int CaseSize);

	/**
	 * 查询案情总记录数
	 * 
	 * @return
	 */
	public long findCaseCount();

	/**
	 * 通过一个关键字查询指定记录数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long findCountByOneParam(String key, Object value);

	/**
	 * 通过一个条件多个值查询指定记录数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long findCountByMultiParams(String key, Object... values);

	/**
	 * 通过一个关键字分页查询案情
	 * 
	 * @param startCount
	 * @param pageSize
	 * @param key
	 * @param value
	 * @return
	 */
	public List<Case> findCasesByOneParam(int startCount, int pageSize, String key, Object value);

	/**
	 * 通过一个条件多个值分页查询案情
	 * 
	 * @param startCount
	 * @param pageSize
	 * @param key
	 * @param values
	 * @return
	 */
	public List<Case> findCasesByMultiParams(int startCount, int pageSize, String key, Object... values);

	/**
	 * 通过ID查询案情信息
	 * 
	 * @param CaseId
	 * @return
	 */
	public Case findCaseById(String CaseId);

	/**
	 * 案情的模糊查询
	 * 
	 * @param startCount
	 * @param pageSize
	 * @param key
	 * @param word
	 * @return
	 */
	public List<Case> findCaseByFuzzy(int startCount, int pageSize, String key, String word);

	/**
	 * 通过内容进行模糊查询记录数
	 * 
	 * @param key
	 * @param word
	 * @return
	 */
	public long findCountByFuzzy(String key, String word);

}
