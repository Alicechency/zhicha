package com.ctbri.iinspection.type;

import java.util.HashMap;
import java.util.Map;

import com.ctbri.common.type.Type;

/**
 * 犯罪预测类型
 * 
 * @author Hogan
 *
 */
public enum PredictionCategory implements Type {

	/**
	 * 一级案情
	 */
	LEVEL_ONE(0, "一级案情"),
	/**
	 * 二级案情
	 */
	LEVEL_TWO(1, "二级案情"),
	/**
	 * 三级案情
	 */
	LEVEL_THREE(2, "三级案情"),

	;

	private int id;
	private String description;

	PredictionCategory(int id, String description) {
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

	private static final Map<Integer, PredictionCategory> DIRC = new HashMap<Integer, PredictionCategory>();

	static {
		for (PredictionCategory e : PredictionCategory.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static PredictionCategory byId(int id) {
		return DIRC.get(id);
	}

}