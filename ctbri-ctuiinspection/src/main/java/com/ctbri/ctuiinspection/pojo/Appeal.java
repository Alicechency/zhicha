package com.ctbri.ctuiinspection.pojo;

/**
 * 诉讼人员实体
 * 
 * @author Hogan
 *
 */
public class Appeal {

	private Integer appealId;
	private String name;
	private String company;
	private Integer type;
	private Integer caseId;

	public Integer getAppealId() {
		return appealId;
	}

	public void setAppealId(Integer appealId) {
		this.appealId = appealId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

}
