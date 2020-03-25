package com.ctbri.dao.neo4j.core;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

import com.ctbri.common.utils.StringUtils;
import com.ctbri.dao.neo4j.pool.Neo4jPool;

/**
 * Neo4j操作模板
 * 
 * @author Hogan
 *
 */
public class Neo4jTemplate {

	private Neo4jPool neo4jPool;

	/**
	 * 无参数查询
	 * 
	 * @param cypher
	 * @param se
	 * @return
	 */
	public <T> T query(String cypher, Neo4jSearchExtractor<T> se) {
		if (StringUtils.isNullOrBlank(cypher)) {
			return null;
		}
		class QueryCallback implements Neo4jSearchCallback<T> {

			@Override
			public T doInSearch(Session session) {
				StatementResult results = session.run(cypher);
				return se.extractData(results);
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 带参数查询
	 * 
	 * @param cypher
	 * @param params
	 * @param se
	 * @return
	 */
	public <T> T query(String cypher, Object[] params, Neo4jSearchExtractor<T> se) {
		if (StringUtils.isNullOrBlank(cypher)) {
			return null;
		}
		class QueryCallback implements Neo4jSearchCallback<T> {

			@Override
			public T doInSearch(Session session) {
				StatementResult results = session.run(loadParamInCypher(cypher, params));
				return se.extractData(results);
			}

		}
		return execute(new QueryCallback());
	}

	/**
	 * 执行查询函数
	 * 
	 * @param searchCallback
	 * @return
	 */
	private <T> T execute(Neo4jSearchCallback<T> searchCallback) {
		Session session = null;
		try {
			session = neo4jPool.borrowObject();
			if (session != null) {
				return searchCallback.doInSearch(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			neo4jPool.returnObject(session);
		}
		return null;
	}

	/**
	 * 在查询语句中装载条件
	 * 
	 * @param cypher
	 * @param params
	 * @return
	 * @throws Neo4jParamNullException
	 */
	private String loadParamInCypher(String cypher, Object[] params) {
		StringBuilder result = new StringBuilder();
		String[] tmp = cypher.split("\\?");
		if (params != null && params.length == tmp.length - 1) {
			for (int i = 0; i < params.length; i++) {
				result.append(tmp[i]);
				result.append(params[i]);
			}
			result.append(tmp[tmp.length - 1]);
		}
		return result.toString();
	}

	public Neo4jPool getNeo4jPool() {
		return neo4jPool;
	}

	public void setNeo4jPool(Neo4jPool neo4jPool) {
		this.neo4jPool = neo4jPool;
	}

}
