package com.ctbri.dao.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * ES连接池工厂
 * 
 * @author Hogan
 *
 */
public class JdbcPoolFactory extends BasePooledObjectFactory<Connection> {

	private JdbcBean jdbcBean;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection create() throws Exception {
		return DriverManager.getConnection(jdbcBean.getUrl(), jdbcBean.getUsername(), jdbcBean.getPassword());
	}

	@Override
	public PooledObject<Connection> wrap(Connection obj) {
		return new DefaultPooledObject<Connection>(obj);
	}

	public JdbcBean getJdbcBean() {
		return jdbcBean;
	}

	public void setJdbcBean(JdbcBean jdbcBean) {
		this.jdbcBean = jdbcBean;
	}

}
