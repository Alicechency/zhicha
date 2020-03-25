package com.ctbri.common.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 连接池顶层接口
 * 
 * @author Hogan
 *
 * @param <T>
 */
public class Pool<T> extends GenericObjectPool<T> {

	public Pool(PooledObjectFactory<T> factory, GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
