package com.ctbri.iinspection.pojo;

/**
 * 犯罪关系实体
 * 
 * @author Hogan
 *
 */
public class CrimeRelationship {

	private Integer crId;
	private Long crimeId;
	private Integer caseType;
	private String caseValue;
	private Float distance;
	private Long nearCrimeId;

	public Integer getCrId() {
		return crId;
	}

	public void setCrId(Integer crId) {
		this.crId = crId;
	}

	public Long getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(Long crimeId) {
		this.crimeId = crimeId;
	}

	public Integer getCaseType() {
		return caseType;
	}

	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}

	public String getCaseValue() {
		return caseValue;
	}

	public void setCaseValue(String caseValue) {
		this.caseValue = caseValue;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Long getNearCrimeId() {
		return nearCrimeId;
	}

	public void setNearCrimeId(Long nearCrimeId) {
		this.nearCrimeId = nearCrimeId;
	}

}
