package com.ctbri.iinspection.pojo;

import java.util.List;

import com.ctbri.iinspection.type.WarningLevel;


/**
 * 嫌疑人实体
 * 
 * @author Hogan
 *
 */
public class Suspect {

	private Integer id;
	private String importantPersonNo;
	private String importantPersonName;
	private String identityNo;
	private String importantPersonDetail;
	private Integer warningLevel;
	private List<SuspectDetail> suspectDetails;
	
	private String warningInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImportantPersonNo() {
		return importantPersonNo;
	}

	public void setImportantPersonNo(String importantPersonNo) {
		this.importantPersonNo = importantPersonNo;
	}

	public String getImportantPersonName() {
		return importantPersonName;
	}

	public void setImportantPersonName(String importantPersonName) {
		this.importantPersonName = importantPersonName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getImportantPersonDetail() {
		return importantPersonDetail;
	}

	public void setImportantPersonDetail(String importantPersonDetail) {
		this.importantPersonDetail = importantPersonDetail;
	}

	public Integer getWarningLevel() {
		return warningLevel;
	}

	public void setWarningLevel(Integer warningLevel) {
		this.warningLevel = warningLevel;
	}

	public List<SuspectDetail> getSuspectDetails() {
		return suspectDetails;
	}

	public void setSuspectDetails(List<SuspectDetail> suspectDetails) {
		this.suspectDetails = suspectDetails;
	}
	
	public String getWarningInfo() {
		if (this.warningLevel != null && this.warningInfo == null) {
			this.warningInfo = WarningLevel.byId(this.warningLevel).getDescription();
		}
		return warningInfo;
	}

	public void setWarningInfo(String warningLevel) {
		this.warningInfo = warningLevel;
	}

}

