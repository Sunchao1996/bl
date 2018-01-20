package com.bl.book.condition.child;

import org.springframework.format.annotation.DateTimeFormat;

import com.bl.book.condition.BaseCondition;

/**
 * @功能描述 图书卡查询条件类
 * @author chao
 *
 */
public class BookCardCond extends BaseCondition{
	private String bookCardId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String endTime;
	private Integer cardStatusId;
	public String getBookCardId() {
		return bookCardId;
	}
	public void setBookCardId(String bookCardId) {
		this.bookCardId = bookCardId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getCardStatusId() {
		return cardStatusId;
	}
	public void setCardStatusId(Integer cardStatusId) {
		this.cardStatusId = cardStatusId;
	}
	
}
