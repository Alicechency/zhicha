package com.ctbri.dao.es.pool;

/**
 * ES连接实体
 * 
 * @author Hogan
 *
 */
public class ESBean {

	/**
	 * 集群名称
	 */
	private String clusterName;
	/**
	 * xpack权限管理字符串
	 */
	private String xpackName;
	/**
	 * IP地址
	 */
	private String ip;
	/**
	 * 端口号
	 */
	private int port;
	/**
	 * 默认的索引名称
	 */
	private String defaultIndex;

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getXpackName() {
		return xpackName;
	}

	public void setXpackName(String xpackName) {
		this.xpackName = xpackName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDefaultIndex() {
		return defaultIndex;
	}

	public void setDefaultIndex(String defaultIndex) {
		this.defaultIndex = defaultIndex;
	}

}
