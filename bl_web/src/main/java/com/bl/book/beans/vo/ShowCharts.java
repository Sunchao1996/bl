package com.bl.book.beans.vo;
/**
 * @功能描述 用来生成报表的数据实体类
 * @author chao
 *
 */
public class ShowCharts {
	private String bookCategoryId;//'图书的类别码',
	private String bookCategory ;//'图书的类别',
	private Integer categoryCount;// '拥有此类别图书的数量',
	private Integer borrowCount;//被借阅数量
	public String getBookCategoryId() {
		return bookCategoryId;
	}
	public void setBookCategoryId(String bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public Integer getCategoryCount() {
		return categoryCount;
	}
	public void setCategoryCount(Integer categoryCount) {
		this.categoryCount = categoryCount;
	}
	public Integer getBorrowCount() {
		return borrowCount;
	}
	public void setBorrowCount(Integer borrowCount) {
		this.borrowCount = borrowCount;
	}
	@Override
	public String toString() {
		return "ShowCharts [bookCategoryId=" + bookCategoryId + ", bookCategory=" + bookCategory + ", categoryCount="
				+ categoryCount + ", borrowCount=" + borrowCount + "]";
	}
	
}
