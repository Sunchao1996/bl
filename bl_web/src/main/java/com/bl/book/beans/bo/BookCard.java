package com.bl.book.beans.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @功能描述 图书卡的实体类S
 * @author chao
 *
 */
public class BookCard {
	private String bookCardId;//图书证编号',
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regTime;//图书证注册时间',
	private Integer cardStatusId;//图书证状态码\r\n            0表示图书证已经注销\r\n            1表示图书证正常使用\r\n            2表示图书证已经挂失，不能使用',
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;//图书证注销时间',
	//辅助字段
	private String endTime_text;//注销时间文本
	private String status_text;
	private String regTime_text;
	public String getRegTime_text(){
		return new SimpleDateFormat("yyyy-MM-dd").format(regTime);
	}
	
	public String getStatus_text() {
		return status_text;
	}
	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}
	public String getEndTime_text() {
		return endTime_text;
	}
	public void setEndTime_text(String endTime_text) {
		this.endTime_text = endTime_text;
	}
	public String getBookCardId() {
		return bookCardId;
	}
	public void setBookCardId(String bookCardId) {
		this.bookCardId = bookCardId;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Integer getCardStatusId() {
		return cardStatusId;
	}
	public void setCardStatusId(Integer cardStatusId) {
		this.cardStatusId = cardStatusId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "BookCard [bookCardId=" + bookCardId + ", regTime=" + regTime + ", cardStatusId=" + cardStatusId
				+ ", endTime=" + endTime + ", endTime_text=" + endTime_text + ", status_text=" + status_text
				+ ", regTime_text=" + regTime_text + "]";
	}
	

}
