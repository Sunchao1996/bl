package com.bl.book.beans.bo;
/**
 * 图书类别对应的实体类
 * @author chao
 *
 */
public class BookCategory {
	private String bookCategoryId;//'图书的类别码',
	private String bookCategory ;//'图书的类别',
	private Integer categoryCount;// '拥有此类别图书的数量',
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
	@Override
	public String toString() {
		return "BookCategory [bookCategoryId=" + bookCategoryId + ", bookCategory=" + bookCategory + ", categoryCount="
				+ categoryCount + "]";
	}
	
}
