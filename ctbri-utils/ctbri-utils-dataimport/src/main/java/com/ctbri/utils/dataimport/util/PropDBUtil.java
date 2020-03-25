package com.ctbri.utils.dataimport.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.ctbri.dao.jdbc.pool.JdbcBean;
import com.ctbri.dao.jdbc.pool.JdbcPool;
import com.ctbri.dao.jdbc.pool.JdbcPoolFactory;
import com.ctbri.utils.dataimport.core.Config;
import com.ctbri.utils.dataimport.core.GlobalProperty;

/**
 * 配置信息
 * 
 * @author Hogan
 *
 */
public class PropDBUtil {

	public static GlobalProperty globalProperty = GlobalProperty.newInstance();
	private static JdbcPool jdbcPool;

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

	public static void update(String key, Object value) throws Exception {
		Connection conn = null;
		StringBuilder sql = new StringBuilder();
		sql.append("update config_coreplatform set ");
		sql.append(key);
		sql.append(" = ?");
		try {
			conn = jdbcPool.borrowObject();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setObject(1, value);
			ps.executeUpdate();
			conn.commit();
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
	}

	public static void update(Map<String, Object> map) throws Exception {
		Connection conn = null;
		StringBuilder sql = new StringBuilder();
		sql.append("update config_coreplatform set ");
		for (String key : map.keySet()) {
			sql.append(key);
			sql.append(" = ? , ");
		}
		sql.append("id = 1 where id = 1");
		try {
			conn = jdbcPool.borrowObject();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int i = 1;
			for (String key : map.keySet()) {
				ps.setObject(i, map.get(key));
				i++;
			}
			ps.executeUpdate();
			conn.commit();
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
	}

	public static Config getConfig() throws Exception {
		Connection conn = null;
		Config config = new Config();
		try {
			conn = jdbcPool.borrowObject();
			PreparedStatement ps = conn.prepareStatement(globalProperty.getMysqlConfigSql());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				config.setSqlCkCreate(rs.getString("sql_ck_create"));
				config.setSqlCkInsert(rs.getString("sql_ck_insert"));
				config.setSqlJqCreate(rs.getString("sql_jq_create"));
				config.setSqlJqInsert(rs.getString("sql_jq_insert"));

				config.setRegexIdentity(rs.getString("regex_identity"));
				config.setRegexLocation(rs.getString("regex_location"));

				config.setEsClusterName(rs.getString("es_cluster_name"));
				config.setEsDefaultIndex(rs.getString("es_default_index"));
				config.setEsIp(rs.getString("es_ip"));
				config.setEsPort(rs.getInt("es_port"));
				break;
			}
			return config;
		} finally {
			if (conn != null) {
				jdbcPool.returnObject(conn);
			}
		}
	}
}
