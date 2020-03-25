package com.ctbri.iinspection.type;

import java.util.HashMap;
import java.util.Map;

import com.ctbri.common.type.Type;

/**
 * 预警级别
 * 
 * @author Hogan
 *
 */
public enum WarningLevel implements Type {

	/**
	 * 红色
	 */
	RED(1, "红色预警"),
	/**
	 * 橙色
	 */
	ORANGE(2, "橙色预警"),
	/**
	 * 黄色
	 */
	YELLOW(3, "黄色预警"),
	/**
	 * 蓝色
	 */
	BULE(4, "蓝色预警"),

	;

	private int id;
	private String description;

	WarningLevel(int id, String description) {
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

	private static final Map<Integer, WarningLevel> DIRC = new HashMap<Integer, WarningLevel>();

	static {
		for (WarningLevel e : WarningLevel.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static WarningLevel byId(int id) {
		return DIRC.get(id);
	}

}