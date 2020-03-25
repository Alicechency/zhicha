package com.ctbri.dao.jdbc.pool;

import java.sql.Connection;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.ctbri.common.pool.Pool;

/**
 * ES连接池
 * 
 * @author Hogan
 *
 */
public class JdbcPool extends Pool<Connection> {

	public JdbcPool(PooledObjectFactory<Connection> factory, GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
