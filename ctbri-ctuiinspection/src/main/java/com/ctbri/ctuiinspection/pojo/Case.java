package com.ctbri.ctuiinspection.pojo;

import com.ctbri.ctuiinspection.type.JudgeResult;

/**
 * 案情实体
 * 
 * @author Hogan
 * 
 */
public class Case {

	private Integer caseId;
	private String title;
	private String badge;
	private String incident;
	private String judgeCause;
	private String judgement;
	private String date;
	private String clerk;
	private Integer judgeResult;
	private String chiefJudge;
	private String judger;

	// 页面使用
	private String judgeResultInfo;

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getIncident() {
		return incident;
	}

	public void setIncident(String incident) {
		this.incident = incident;
	}

	public String getJudgeCause() {
		return judgeCause;
	}

	public void setJudgeCause(String judgeCause) {
		this.judgeCause = judgeCause;
	}

	public String getJudgement() {
		return judgement;
	}

	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}

	public Integer getJudgeResult() {
		return judgeResult;
	}

	public void setJudgeResult(Integer judgeResult) {
		this.judgeResult = judgeResult;
	}

	public String getChiefJudge() {
		return chiefJudge;
	}

	public void setChiefJudge(String chiefJudge) {
		this.chiefJudge = chiefJudge;
	}

	public String getJudger() {
		return judger;
	}

	public void setJudger(String judger) {
		this.judger = judger;
	}

	public String getJudgeResultInfo() {
		if (this.judgeResult != null) {
			judgeResultInfo = JudgeResult.byId(this.judgeResult).getDescription();
		}
		return judgeResultInfo;
	}

	public void setJudgeResultInfo(String judgeResultInfo) {
		this.judgeResultInfo = judgeResultInfo;
	}

}
