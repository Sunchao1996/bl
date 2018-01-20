package com.bl.book.beans.bo;
/**
 *图书状态信息表
 * @author chao
 *
 */
public class BookStatus {
	private Integer bookStatusId;//'图书的状态码',
	private String bookStatus;//'图书的状态',
	public Integer getBookStatusId() {
		return bookStatusId;
	}
	public void setBookStatusId(Integer bookStatusId) {
		this.bookStatusId = bookStatusId;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	@Override
	public String toString() {
		return "BookStatus [bookStatusId=" + bookStatusId + ", bookStatus=" + bookStatus + "]";
	}
	
}
