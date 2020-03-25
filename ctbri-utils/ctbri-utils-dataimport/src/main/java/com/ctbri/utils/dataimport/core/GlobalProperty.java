package com.ctbri.utils.dataimport.core;

import com.ctbri.utils.dataimport.util.Consts;
import com.ctbri.utils.dataimport.util.PropDBUtil;
import com.ctbri.utils.dataimport.util.PropUtil;
import com.ctbri.utils.dataimport.util.ScriptUtil;

/**
 * 全局的配置信息
 * 
 * @author Hogan
 *
 */
public class GlobalProperty {

	private static GlobalProperty globalProperty = new GlobalProperty();

	private static String mysqlUrl;
	private static String mysqlDriver;
	private static String mysqlUsername;
	private static String mysqlPassword;
	private static String mysqlDbName;
	private static String mysqlConfigSql;

	private static Integer druidInitialSize;
	private static Integer druidMinIdle;
	private static Integer druidMaxActive;

	private boolean isDump;
	private boolean isPushES;
	private boolean isInit;
	private boolean isProduce;
	private boolean isConfig;

	private static Config config;

	static {
		mysqlUrl = PropUtil.getValue("mysql.url");
		mysqlUsername = PropUtil.getValue("mysql.username");
		mysqlPassword = PropUtil.getValue("mysql.password");
		mysqlDriver = PropUtil.getValue("mysql.driver");
		mysqlConfigSql = PropUtil.getValue("mysql.config.sql");
		druidInitialSize = Integer.parseInt(PropUtil.getValue("druid.initialSize"));
		druidMinIdle = Integer.parseInt(PropUtil.getValue("druid.minIdle"));
		druidMaxActive = Integer.parseInt(PropUtil.getValue("druid.maxActive"));
	}

	private GlobalProperty() {
	}

	public static GlobalProperty newInstance() {
		return globalProperty;
	}

	public static void refreshConfig(String username,String password) {
		mysqlUsername = username;
		mysqlPassword = password;
		try {
			ScriptUtil.exceuteScript(Consts.SCRIPT_CONFIG_NAME);
			config = PropDBUtil.getConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMysqlUrl() {
		return mysqlUrl;
	}

	public String getMysqlDriver() {
		return mysqlDriver;
	}

	public String getMysqlUsername() {
		return mysqlUsername;
	}

	public String getMysqlPassword() {
		return mysqlPassword;
	}

	public Integer getDruidInitialSize() {
		return druidInitialSize;
	}

	public Integer getDruidMinIdle() {
		return druidMinIdle;
	}

	public Integer getDruidMaxActive() {
		return druidMaxActive;
	}

	public String getSqlCreate(Integer type) {
		if (type == Consts.SOURCE_TYPE_JQ) {
			return config.getSqlJqCreate();
		} else if (type == Consts.SOURCE_TYPE_CK) {
			return config.getSqlCkCreate();
		}
		return null;
	}

	public String getSqlInsert(Integer type) {
		if (type == Consts.SOURCE_TYPE_JQ) {
			return config.getSqlJqInsert();
		} else if (type == Consts.SOURCE_TYPE_CK) {
			return config.getSqlCkInsert();
		}
		return null;
	}

	public String getMysqlDbName() {
		return mysqlDbName;
	}

	public String getEsClusterName() {
		return config.getEsClusterName();
	}

	public String getEsIp() {
		return config.getEsIp();
	}

	public Integer getEsPort() {
		return config.getEsPort();
	}

	public String getEsDefaultIndex() {
		return config.getEsDefaultIndex();
	}

	public String getMysqlConfigSql() {
		return mysqlConfigSql;
	}

	public String getRegexIdentity() {
		return config.getRegexIdentity();
	}

	public String getRegexLocation() {
		return config.getRegexLocation();
	}

	public void setIsDump(boolean isDump) {
		this.isDump = isDump;
	}

	public boolean getIsDump() {
		return isDump;
	}

	public boolean isPushES() {
		return isPushES;
	}

	public void setPushES(boolean isPushES) {
		this.isPushES = isPushES;
	}

	public boolean isConfig() {
		return isConfig;
	}

	public void setConfig(boolean isConfig) {
		this.isConfig = isConfig;
	}

	public boolean isInit() {
		return isInit;
	}

	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}

	public boolean isProduce() {
		return isProduce;
	}

	public void setProduce(boolean isProduce) {
		this.isProduce = isProduce;
	}

}
