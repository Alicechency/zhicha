package com.ctbri.iinspection.pojo;

/**
 * 嫌疑人关系实体
 * 
 * @author Hogan
 *
 */
public class SupervisoryAssociation {

	private Integer id;
	private String startPersonId;
	private String startPersonName;
	private Integer startCategory;
	private String endPersonId;
	private String endPersonName;
	private Integer endCategory;
	private int frequency;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartPersonId() {
		return startPersonId;
	}

	public void setStartPersonId(String startPersonId) {
		this.startPersonId = startPersonId;
	}

	public String getStartPersonName() {
		return startPersonName;
	}

	public void setStartPersonName(String startPersonName) {
		this.startPersonName = startPersonName;
	}

	public Integer getStartCategory() {
		return startCategory;
	}

	public void setStartCategory(Integer startCategory) {
		this.startCategory = startCategory;
	}

	public String getEndPersonId() {
		return endPersonId;
	}

	public void setEndPersonId(String endPersonId) {
		this.endPersonId = endPersonId;
	}

	public String getEndPersonName() {
		return endPersonName;
	}

	public void setEndPersonName(String endPersonName) {
		this.endPersonName = endPersonName;
	}

	public Integer getEndCategory() {
		return endCategory;
	}

	public void setEndCategory(Integer endCategory) {
		this.endCategory = endCategory;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}
