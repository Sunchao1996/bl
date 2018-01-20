package com.bl.book.beans.vo;
/**
 * 分页查询的父类
 * @author chao
 *
 */
public class Page {
	protected Integer pageNo;
	protected Integer pageSize;
	protected Integer pageCount;
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
