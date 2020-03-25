package com.ctbri.dao.neo4j.core;

import org.neo4j.driver.v1.Session;

import com.ctbri.common.dao.SearchCallback;

/**
 * 查询回调函数
 * 
 * @author Hogan
 *
 * @param <T>
 */
public interface Neo4jSearchCallback<T> extends SearchCallback<T, Session> {

}