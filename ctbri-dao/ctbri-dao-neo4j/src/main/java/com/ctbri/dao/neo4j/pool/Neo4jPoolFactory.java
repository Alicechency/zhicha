package com.ctbri.dao.neo4j.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;

/**
 * ES连接池工厂
 * 
 * @author Hogan
 *
 */
public class Neo4jPoolFactory extends BasePooledObjectFactory<Session> {

	private Neo4jBean neo4jBean;

	@Override
	public Session create() throws Exception {
		return GraphDatabase
				.driver(neo4jBean.getUrl(), AuthTokens.basic(neo4jBean.getUsername(), neo4jBean.getPassword()))
				.session();
	}

	@Override
	public PooledObject<Session> wrap(Session obj) {
		return new DefaultPooledObject<Session>(obj);
	}

	public Neo4jBean getNeo4jBean() {
		return neo4jBean;
	}

	public void setNeo4jBean(Neo4jBean neo4jBean) {
		this.neo4jBean = neo4jBean;
	}

}
