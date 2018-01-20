package com.bl.book.beans.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @功能描述 图书入馆时间信息表
 * @author chao
 *
 */
public class BookIntoTime {
	private Integer id;
	private String bookBarCode;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date intoTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookBarCode() {
		return bookBarCode;
	}
	public void setBookBarCode(String bookBarCode) {
		this.bookBarCode = bookBarCode;
	}
	public Date getIntoTime() {
		return intoTime;
	}
	public void setIntoTime(Date intoTime) {
		this.intoTime = intoTime;
	}
	@Override
	public String toString() {
		return "BookIntoTime [id=" + id + ", bookBarCode=" + bookBarCode + ", intoTime=" + intoTime + "]";
	}
	

}
