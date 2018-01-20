package com.bl.book.beans.bo;

public class BookSeat {
	private String bookSeat;//图书的位置
	private Integer seatStatus;//'0表示此位置没有书
	public String getBookSeat() {
		return bookSeat;
	}
	public void setBookSeat(String bookSeat) {
		this.bookSeat = bookSeat;
	}
	public Integer getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(Integer seatStatus) {
		this.seatStatus = seatStatus;
	}
	
}
