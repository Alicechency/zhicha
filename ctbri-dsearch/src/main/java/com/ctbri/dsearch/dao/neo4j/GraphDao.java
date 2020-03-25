package com.ctbri.dsearch.dao.neo4j;

import java.util.Map;

/**
 * neo4j图形数据层接口
 * 
 * @author Hogan
 *
 */
public interface GraphDao {

	/**
	 * 查询与word相关的词
	 * 
	 * @param word
	 * @param limit
	 * @return
	 */
	public Map<String, Double> findRelationWords(String word, int limit);

}
