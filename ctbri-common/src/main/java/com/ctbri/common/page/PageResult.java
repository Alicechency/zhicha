package com.ctbri.common.page;

import java.util.List;

/**
 * 分页实体类
 * 
 * @author Hogan
 *
 */
public class PageResult<T> {

	/**
	 * 当前页码
	 */
	private int currentPage;
	/**
	 * 每页记录数
	 */
	private int perPage;
	/**
	 * 总页数
	 */
	private int sumPage;
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 分页结果
	 */
	private List<T> records;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage < 0 ? 1 : currentPage;
	}

	public int getSumPage() {
		sumPage = (int) (this.total % this.perPage == 0 ? 
					this.total / this.perPage 
				  : this.total / this.perPage + 1);
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

}
