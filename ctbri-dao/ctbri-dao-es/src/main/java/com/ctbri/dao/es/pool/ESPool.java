package com.ctbri.dao.es.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.elasticsearch.client.transport.TransportClient;

import com.ctbri.common.pool.Pool;

/**
 * ES连接池
 * 
 * @author Hogan
 *
 */
public class ESPool extends Pool<TransportClient> {

	public ESPool(PooledObjectFactory<TransportClient> factory, GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
