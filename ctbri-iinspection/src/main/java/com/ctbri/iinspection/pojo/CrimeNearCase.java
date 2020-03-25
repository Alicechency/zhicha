package com.ctbri.iinspection.pojo;

/***
 * 对应crime_brief_nearcasebyhour的实体类
 * 
 * @author wkhuahuo
 * 
 */
public class CrimeNearCase {

	private String crimeId;
	private String byHour;
	private Double sumByHourCount;

	public String getByHour() {
		return byHour;
	}

	public void setByHour(String byHour) {
		this.byHour = byHour;
	}

	public Double getSumByHourCount() {
		return sumByHourCount;
	}

	public void setSumByHourCount(Double sumByHourCount) {
		this.sumByHourCount = sumByHourCount;
	}

	public String getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(String crimeId) {
		this.crimeId = crimeId;
	}

}
