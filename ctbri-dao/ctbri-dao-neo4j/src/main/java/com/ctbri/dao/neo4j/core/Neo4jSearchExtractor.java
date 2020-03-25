package com.ctbri.dao.neo4j.core;


import org.neo4j.driver.v1.StatementResult;

import com.ctbri.common.dao.SearchExtractor;

/**
 * 搜索结果封装
 * 
 * @author Hogan
 *
 * @param <T>
 */
public interface Neo4jSearchExtractor<T> extends SearchExtractor<T, StatementResult>{

}
