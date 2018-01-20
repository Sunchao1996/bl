package com.bl.book.condition.child;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bl.book.condition.BaseCondition;
/**
 * @功能描述 图书借阅信息的查询条件实体类
 * @author chao
 *
 */
public class BorrowInfoCond extends BaseCondition {
	private String readerId;//读者编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startBorrowTime;//借书开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endBorrowTime;//借书结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startBackTime;//应还起始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endBackTime;//应还结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startReturnTime;//还书起始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endReturnTime;//还书结束时间
	private Integer borrowStatus = -1;//记录的状态        0表示未还           1表示已还
	private String bookSeat;//图书的位置
	private String bookBarCode;//图书的条形码
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	
	public Date getStartBorrowTime() {
		return startBorrowTime;
	}
	public void setStartBorrowTime(Date startBorrowTime) {
		this.startBorrowTime = startBorrowTime;
	}
	public Date getEndBorrowTime() {
		return endBorrowTime;
	}
	public void setEndBorrowTime(Date endBorrowTime) {
		this.endBorrowTime = endBorrowTime;
	}
	public Date getStartBackTime() {
		return startBackTime;
	}
	public void setStartBackTime(Date startBackTime) {
		this.startBackTime = startBackTime;
	}
	public Date getEndBackTime() {
		return endBackTime;
	}
	public void setEndBackTime(Date endBackTime) {
		this.endBackTime = endBackTime;
	}
	public Date getStartReturnTime() {
		return startReturnTime;
	}
	public void setStartReturnTime(Date startReturnTime) {
		this.startReturnTime = startReturnTime;
	}
	public Date getEndReturnTime() {
		return endReturnTime;
	}
	public void setEndReturnTime(Date endReturnTime) {
		this.endReturnTime = endReturnTime;
	}
	public Integer getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(Integer borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
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
	@Override
	public String toString() {
		return "BorrowInfoCond [readerId=" + readerId + ", startBorrowTime=" + startBorrowTime + ", endBorrowTime="
				+ endBorrowTime + ", startBackTime=" + startBackTime + ", endBackTime=" + endBackTime
				+ ", startReturnTime=" + startReturnTime + ", endReturnTime=" + endReturnTime + ", borrowStatus="
				+ borrowStatus + ", bookSeat=" + bookSeat + "]";
	}
	
	
}
