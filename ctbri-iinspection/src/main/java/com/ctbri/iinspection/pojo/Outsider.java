package com.ctbri.iinspection.pojo;

/**
 * 外来人员实体
 * 
 * @author Hogan
 *
 */
public class Outsider {

    private int id;
	private Double longitude;
	private Double latitude;
	private int uniqueUsers;
    private String date;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

    public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }
  
    public String getDate() {
        return date;
    }
  
    public void setDate(String date) {
        this.date = date;
    }

    public int getUniqueUsers() {
        return uniqueUsers;
    }

    public void setUniqueUsers(int uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }

    
    
} 
