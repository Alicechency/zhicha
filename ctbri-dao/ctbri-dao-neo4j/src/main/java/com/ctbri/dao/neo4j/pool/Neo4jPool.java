package com.ctbri.dao.neo4j.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.neo4j.driver.v1.Session;

import com.ctbri.common.pool.Pool;

/**
 * ES连接池
 * 
 * @author Hogan
 *
 */
public class Neo4jPool extends Pool<Session> {

	public Neo4jPool(PooledObjectFactory<Session> factory, GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
