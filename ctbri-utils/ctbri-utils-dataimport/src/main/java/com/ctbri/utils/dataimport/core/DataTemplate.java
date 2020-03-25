package com.ctbri.utils.dataimport.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import com.ctbri.dao.jdbc.pool.JdbcBean;
import com.ctbri.dao.jdbc.pool.JdbcPool;
import com.ctbri.dao.jdbc.pool.JdbcPoolFactory;

/**
 * 数据操作模板
 * 
 * @author Hogan
 *
 */
public class DataTemplate {

	public static GlobalProperty globalProperty = GlobalProperty.newInstance();
	private static JdbcPool jdbcPool;
	private Logger logger = Logger.getLogger(getClass());

	static {
		JdbcBean jdbcBean = new JdbcBean();
		jdbcBean.setUrl(globalProperty.getMysqlUrl());
		jdbcBean.setPassword(globalProperty.getMysqlPassword());
		jdbcBean.setUsername(globalProperty.getMysqlUsername());
		JdbcPoolFactory factory = new JdbcPoolFactory();
		factory.setJdbcBean(jdbcBean);
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(20);
		config.setMinIdle(10);
		config.setMaxIdle(10);
		jdbcPool = new JdbcPool(factory, config);
	}

	/**
	 * 执行不带参数的sql语句
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	public void excute(String sql) throws SQLException {
		Connection conn = null;
		try {
			conn = jdbcPool.borrowObject();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
	}

	/**
	 * 是否存在
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public boolean isExist(String sql, Object param) {
		Connection conn = null;
		try {
			conn = jdbcPool.borrowObject();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, param);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
		return false;
	}

	/**
	 * 是否存在
	 * 
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public void callProcedure(String procedure) {
		Connection conn = null;
		try {
			conn = jdbcPool.borrowObject();
			PreparedStatement ps = conn.prepareCall(procedure);
			ps.executeQuery();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
	}

	/**
	 * 批量插入
	 * 
	 * @param sql
	 * @param values
	 * @throws SQLException
	 */
	public void batchInsert(String sql, List<List<String>> values) throws SQLException {
		Connection conn = null;
		try {
			conn = jdbcPool.borrowObject();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			for (List<String> value : values) {
				if (!"警情序号".equals(value.get(0))) {
					for (int i = 0; i < value.size(); i++) {
						ps.setObject(i + 1, value.get(i));
					}
					ps.addBatch();
				}
			}
			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			logger.error(e);
			conn.rollback();
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
	}

}
