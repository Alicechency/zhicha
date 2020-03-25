package com.ctbri.iinspection.pojo;

/**
 * 对crime_brief表的对象类
 * 
 * @author wkhuahuo
 *
 */
public class CrimeBrief {

	private String crimeId;
	private String crimeBrief;
	private Double debug;

	public String getCrimeBrief() {
		return crimeBrief;
	}

	public void setCrimeBrief(String crimeBrief) {
		this.crimeBrief = crimeBrief;
	}

	public Double getDebug() {
		return debug;
	}

	public void setDebug(Double debug) {
		this.debug = debug;
	}

	public String getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(String crimeId) {
		this.crimeId = crimeId;
	}
}
