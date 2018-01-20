package com.bl.book.beans.bo;
/**
 *图书在馆状态和位置
 * @author chao
 *
 */
public class BookPrimary {
	private String bookSeat;//'图书的位置',
	private String bookBarCode ;//'图书的条形码',
	private Integer bookStatusId ;//'图书的状态码',
	private Integer bookBorrowCount;//'图书的借阅次数最少是0次',
	public String getBookSeat() {
		return bookSeat;
	}
	public void setBookSeat(String bookSeat) {
		this.bookSeat = bookSeat;
	}
	public String getBookBarCode() {
		return bookBarCode;
	}
	public void setBookBarCode(String bookBarCode) {
		this.bookBarCode = bookBarCode;
	}
	public Integer getBookStatusId() {
		return bookStatusId;
	}
	public void setBookStatusId(Integer bookStatusId) {
		this.bookStatusId = bookStatusId;
	}
	public Integer getBookBorrowCount() {
		return bookBorrowCount;
	}
	public void setBookBorrowCount(Integer bookBorrowCount) {
		this.bookBorrowCount = bookBorrowCount;
	}
	@Override
	public String toString() {
		return "BookPrimary [bookSeat=" + bookSeat + ", bookBarCode=" + bookBarCode + ", bookStatusId=" + bookStatusId
				+ ", bookBorrowCount=" + bookBorrowCount + "]";
	}
	
}
