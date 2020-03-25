package com.ctbri.utils.dataimport.core;

/**
 * 配置信息实体
 * 
 * @author Hogan
 *
 */
public class Config {

	private String sqlJqCreate;
	private String sqlJqInsert;
	private String sqlCkCreate;
	private String sqlCkInsert;

	private String regexIdentity;
	private String regexLocation;

	private String esClusterName;
	private String esIp;
	private Integer esPort;
	private String esDefaultIndex;

	public String getSqlJqCreate() {
		return sqlJqCreate;
	}

	public void setSqlJqCreate(String sqlJqCreate) {
		this.sqlJqCreate = sqlJqCreate;
	}

	public String getSqlJqInsert() {
		return sqlJqInsert;
	}

	public void setSqlJqInsert(String sqlJqInsert) {
		this.sqlJqInsert = sqlJqInsert;
	}

	public String getSqlCkCreate() {
		return sqlCkCreate;
	}

	public void setSqlCkCreate(String sqlCkCreate) {
		this.sqlCkCreate = sqlCkCreate;
	}

	public String getSqlCkInsert() {
		return sqlCkInsert;
	}

	public void setSqlCkInsert(String sqlCkInsert) {
		this.sqlCkInsert = sqlCkInsert;
	}

	public String getRegexIdentity() {
		return regexIdentity;
	}

	public void setRegexIdentity(String regexIdentity) {
		this.regexIdentity = regexIdentity;
	}

	public String getRegexLocation() {
		return regexLocation;
	}

	public void setRegexLocation(String regexLocation) {
		this.regexLocation = regexLocation;
	}

	public String getEsClusterName() {
		return esClusterName;
	}

	public void setEsClusterName(String esClusterName) {
		this.esClusterName = esClusterName;
	}

	public String getEsIp() {
		return esIp;
	}

	public void setEsIp(String esIp) {
		this.esIp = esIp;
	}

	public Integer getEsPort() {
		return esPort;
	}

	public void setEsPort(Integer esPort) {
		this.esPort = esPort;
	}

	public String getEsDefaultIndex() {
		return esDefaultIndex;
	}

	public void setEsDefaultIndex(String esDefaultIndex) {
		this.esDefaultIndex = esDefaultIndex;
	}

}
