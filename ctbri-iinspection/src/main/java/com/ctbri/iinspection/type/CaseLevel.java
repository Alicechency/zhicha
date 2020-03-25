package com.ctbri.iinspection.type;

import java.util.HashMap;
import java.util.Map;

import com.ctbri.common.type.Type;

/**
 * 案情级别
 * 
 * @author Hogan
 *
 */
public enum CaseLevel implements Type {

	/**
	 * 刑事类警情
	 */
	LEVEL_ONE(0, "刑事类警情"),
	/**
	 * 治安类警情
	 */
	LEVEL_TWO(1, "治安类警情"),
	/**
	 * 求助分析类警情
	 */
	LEVEL_THREE(2, "纠纷类警情"),
	/**
	 * 民意类警情
	 */
	LEVEL_FOUR(3, "民意类警情"),

	;

	private int id;
	private String description;

	CaseLevel(int id, String description) {
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

	private static final Map<Integer, CaseLevel> DIRC = new HashMap<Integer, CaseLevel>();

	static {
		for (CaseLevel e : CaseLevel.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static CaseLevel byId(int id) {
		return DIRC.get(id);
	}

}