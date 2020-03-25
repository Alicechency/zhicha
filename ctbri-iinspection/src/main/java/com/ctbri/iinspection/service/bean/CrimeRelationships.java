package com.ctbri.iinspection.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 案情关联业务实体
 * 
 * @author Hogan
 *
 */
public class CrimeRelationships {

	private Long id;
	private String identity;
	private List<String> nearCaseValues;
	private List<Float> nearCaseDistances;
	private List<Long> nearCaseIds;
	private List<String> suspectValues;
	private List<Float> suspectDistances;
	
	public CrimeRelationships(){
		nearCaseValues = new ArrayList<String>();
		nearCaseDistances = new ArrayList<Float>();
		nearCaseIds = new ArrayList<Long>();
		suspectValues = new ArrayList<String>();
		suspectDistances = new ArrayList<Float>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public List<String> getNearCaseValues() {
		return nearCaseValues;
	}

	public void setNearCaseValues(List<String> nearCaseValues) {
		this.nearCaseValues = nearCaseValues;
	}

	public List<Float> getNearCaseDistances() {
		return nearCaseDistances;
	}

	public void setNearCaseDistances(List<Float> nearCaseDistances) {
		this.nearCaseDistances = nearCaseDistances;
	}

	public List<Long> getNearCaseIds() {
		return nearCaseIds;
	}

	public void setNearCaseIds(List<Long> nearCaseIds) {
		this.nearCaseIds = nearCaseIds;
	}

	public List<String> getSuspectValues() {
		return suspectValues;
	}

	public void setSuspectValues(List<String> suspectValues) {
		this.suspectValues = suspectValues;
	}

	public List<Float> getSuspectDistances() {
		return suspectDistances;
	}

	public void setSuspectDistances(List<Float> suspectDistances) {
		this.suspectDistances = suspectDistances;
	}

}
