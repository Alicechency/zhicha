package com.ctbri.iinspection.service.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ctbri.iinspection.type.SupervisoryCategory;

/**
 * 嫌疑人关系业务实体
 * 
 * @author Hogan
 *
 */
public class SupervisoryAssociations {

	private Set<String> suspectIds;
	private List<Node> nodes;
	private List<Link> links;
	private List<Category> categories;
	private List<String> categoryName;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<String> getCategoryName() {
		if (this.categoryName == null) {
			List<String> result = new ArrayList<String>();
			if (this.getCategories() != null) {
				for (Category e : this.getCategories()) {
					result.add(e.getName());
				}
			}
			this.categoryName = result;
		}
		return categoryName;
	}

	public void setCategoryName(List<String> categoryName) {
		this.categoryName = categoryName;
	}

	public Set<String> getSuspectIds() {
		return suspectIds;
	}

	public void setSuspectIds(Set<String> suspectIds) {
		this.suspectIds = suspectIds;
	}

	/**
	 * 关系结点实体
	 * 
	 * @author Hogan
	 *
	 */
	public static class Node {

		private String value;
		private String name;
		private Integer symbolSize;
		private String category;
		private String draggable;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getSymbolSize() {
			return symbolSize;
		}

		public void setSymbolSize(Integer symbolSize) {
			this.symbolSize = symbolSize;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(Integer category) {
			this.category = SupervisoryCategory.byId(category).getDescription();
		}

		public String isDraggable() {
			return draggable;
		}

		public void setDraggable(String draggable) {
			this.draggable = draggable;
		}

	}

	/**
	 * 关系连接实体
	 * 
	 * @author Hogan
	 *
	 */
	public static class Link {

		private String source;
		private String target;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

	}

	/**
	 * 关系类型实体
	 * 
	 * @author Hogan
	 *
	 */
	public static class Category {

		private String name;

		public String getName() {
			return name;
		}

		public void setName(Integer category) {
			this.name = SupervisoryCategory.byId(category).getDescription();
		}

	}
}
