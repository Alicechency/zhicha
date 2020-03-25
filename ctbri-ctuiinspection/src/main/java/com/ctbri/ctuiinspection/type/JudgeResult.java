package com.ctbri.ctuiinspection.type;

import java.util.HashMap;
import java.util.Map;

import com.ctbri.common.type.Type;

/**
 * 案情级别
 * 
 * @author Hogan
 *
 */
public enum JudgeResult implements Type {

	/**
	 * 原告胜
	 */
	PLAINTIFF_SUCCESS(0, "原告胜"),
	/**
	 * 被告胜
	 */
	DEFENDENT_SUCCESS(1, "被告胜"),
	/**
	 * 撤诉
	 */
	WITHDRAWAL(2, "撤诉"),
	/**
	 * 重申
	 */
	REITERATE(3, "重申"),

	;

	private int id;
	private String description;

	JudgeResult(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static final Map<Integer, JudgeResult> DIRC = new HashMap<Integer, JudgeResult>();

	static {
		for (JudgeResult e : JudgeResult.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static JudgeResult byId(int id) {
		return DIRC.get(id);
	}

}