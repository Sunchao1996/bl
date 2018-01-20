package com.bl.book.beans.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @功能描述 首页公告的实体类
 * @author chao
 *
 */
public class Notice {
	private Integer id;
	private String noticeTitle;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date noticeTime;
	private String noticeContent;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", noticeTitle=" + noticeTitle + ", noticeTime=" + noticeTime + ", noticeContent="
				+ noticeContent + "]";
	}
	
	
}
