package com.ctbri.ctuiinspection.pojo;

/**
 * 代理律师实体
 * 
 * @author Hogan
 *
 */
public class Lawyer {

	private Integer lawyerId;
	private String name;
	private Integer type;
	private Integer caseId;

	private String topic;
	private Integer winningPercentage;
	private Integer judgeResult;

	public Integer getLawyerId() {
		return lawyerId;
	}

	public void setLawyerId(Integer lawyerId) {
		this.lawyerId = lawyerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getWinningPercentage() {
		return winningPercentage;
	}

	public void setWinningPercentage(Integer winningPercentage) {
		this.winningPercentage = winningPercentage;
	}

	public Integer getJudgeResult() {
		return judgeResult;
	}

	public void setJudgeResult(Integer judgeResult) {
		this.judgeResult = judgeResult;
	}

}
