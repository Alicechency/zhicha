package com.ctbri.iinspection.pojo;

/**
 * 嫌疑人详情实体
 * 
 * @author Hogan
 *
 */
public class SuspectDetail {

	private Integer id;
	private String importantPersonNo;
	private String activityDate;
	private String activityLocation;
	private String socialLocation;
	private String activityDetail;
	private String suggestion;
	private Double activityLongtitude;
	private Double activityLatitude;

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

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityLocation() {
		return activityLocation;
	}

	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
	}

	public String getSocialLocation() {
		return socialLocation;
	}

	public void setSocialLocation(String socialLocation) {
		this.socialLocation = socialLocation;
	}

	public String getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Double getActivityLongtitude() {
		return activityLongtitude;
	}

	public void setActivityLongtitude(Double activityLongtitude) {
		this.activityLongtitude = activityLongtitude;
	}

	public Double getActivityLatitude() {
		return activityLatitude;
	}

	public void setActivityLatitude(Double activityLatitude) {
		this.activityLatitude = activityLatitude;
	}

}
