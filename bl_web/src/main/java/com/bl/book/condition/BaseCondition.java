package com.bl.book.condition;
/**
 * @功能描述 分页查询的基本条件类
 * @author chao
 *
 */
public class BaseCondition {

	protected Integer pageNo;//当前页
	protected Integer startLine;//起始行
	protected Integer total;//总记录数
	protected Integer pageCount;//总页数
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	protected Integer pageSize;//每页大小
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getStartLine() {
		return startLine;
	}
	public void setStartLine(Integer startLine) {
		this.startLine = startLine;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
