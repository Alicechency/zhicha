package com.ctbri.ctuiinspection.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 关系类别
 * 
 * @author Hogan
 *
 */
public enum RelationCategory {

	/**
	 * 律师
	 */
	LAWYER("lawyer", "律师"),
	/**
	 * 案件
	 */
	CASE("case", "案件"),
	/**
	 * 相关人员
	 */
	RELATION_PEOPLE("idpeo", "相关人员"),
	
	;

	private String id;
	private String description;

	RelationCategory(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private static final Map<String, RelationCategory> DIRC = new HashMap<String, RelationCategory>();

	static {
		for (RelationCategory e : RelationCategory.values()) {
			DIRC.put(e.getId(), e);
		}
	}

	public static RelationCategory byId(String id) {
		return DIRC.get(id);
	}

}