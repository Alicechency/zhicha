package com.ctbri.iinspection.pojo;

import com.ctbri.iinspection.type.CaseLevel;

/**
 * 案情实体
 * 
 * @author Hogan
 * 
 */
public class Case {

	private String id;
	private String author;
	private String title;
	private String content;
	private String submitDate;
	private Integer category;
	
	private String level;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getLevel() {
		if (this.level == null && this.category != null) {
			this.level = CaseLevel.byId(this.category).getDescription();
		}
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
