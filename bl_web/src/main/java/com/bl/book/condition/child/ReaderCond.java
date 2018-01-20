package com.bl.book.condition.child;

import com.bl.book.condition.BaseCondition;

/**
 * @功能描述 分页查询读者的条件类
 * @author chao
 *
 */
public class ReaderCond extends BaseCondition{
	private String readerName;	
	private String readerTel;		
	private Integer readerStatusId;
	private String bookCardId;
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReaderTel() {
		return readerTel;
	}
	public void setReaderTel(String readerTel) {
		this.readerTel = readerTel;
	}
	public Integer getReaderStatusId() {
		return readerStatusId;
	}
	public void setReaderStatusId(Integer readerStatusId) {
		this.readerStatusId = readerStatusId;
	}
	public String getBookCardId() {
		return bookCardId;
	}
	public void setBookCardId(String bookCardId) {
		this.bookCardId = bookCardId;
	}
	
}
