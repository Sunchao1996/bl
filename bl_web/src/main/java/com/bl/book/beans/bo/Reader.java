package com.bl.book.beans.bo;
/**
 * @功能描述 读者的实体类
 * @author chao
 *
 */
public class Reader {
	private String readerId;//读者编号',将身份证号作为读者编号
	private String readerName;//读者姓名',
	private String readerGender;//读者性别',
	private String readerTel;//读者联系电话'
	private String readerCardId;//读者身份证号码',将后六位提取,作为密码
	private Integer readerStatusId;//读者状态码\r\n            0表示已经注销\r\n            1表示正常使用',
	private String bookCardId;//图书证编号',作为读者用户的用户名
	
	//辅助显示字段
	private String readerStatus;//读者状态显示的文本
	private String bookCardStatus;//读者图书卡状态
	
	public String getBookCardStatus() {
		return bookCardStatus;
	}
	public void setBookCardStatus(String bookCardStatus) {
		this.bookCardStatus = bookCardStatus;
	}
	public String getReaderStatus() {
		return readerStatus;
	}
	public void setReaderStatus(String readerStatus) {
		this.readerStatus = readerStatus;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getReaderGender() {
		return readerGender;
	}
	public void setReaderGender(String readerGender) {
		this.readerGender = readerGender;
	}
	public String getReaderTel() {
		return readerTel;
	}
	public void setReaderTel(String readerTel) {
		this.readerTel = readerTel;
	}
	public String getReaderCardId() {
		return readerCardId;
	}
	public void setReaderCardId(String readerCardId) {
		this.readerCardId = readerCardId;
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
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", readerName=" + readerName + ", readerGender=" + readerGender
				+ ", readerTel=" + readerTel + ", readerCardId=" + readerCardId + ", readerStatusId=" + readerStatusId
				+ ", bookCardId=" + bookCardId + ", readerStatus=" + readerStatus + ", bookCardStatus=" + bookCardStatus
				+ "]";
	}
	
	
}
