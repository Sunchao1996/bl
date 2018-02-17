package com.bl.book.condition.child;

import java.text.SimpleDateFormat;
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
	private String bookCategoryId;//图书的类别

	public String getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(String bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getStartBorrowTimeStr(){
		if(startBorrowTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(startBorrowTime);
		}else{
			return "";
		}
	}
	public Date getStartBorrowTime() {
		return startBorrowTime;
	}
	public void setStartBorrowTime(Date startBorrowTime) {

		this.startBorrowTime = startBorrowTime;
	}
	public String getEndBorrowTimeStr(){
		if(endBorrowTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(endBorrowTime);
		}else{
			return "";
		}
	}
	public Date getEndBorrowTime() {
		return endBorrowTime;
	}
	public void setEndBorrowTime(Date endBorrowTime) {
		this.endBorrowTime = endBorrowTime;
	}
	public String getStartBackTimeStr(){
		if(startBackTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(startBackTime);
		}else{
			return "";
		}
	}
	public Date getStartBackTime() {
		return startBackTime;
	}
	public void setStartBackTime(Date startBackTime) {
		this.startBackTime = startBackTime;
	}
	public String getEndBackTimeStr(){
		if(endBackTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(endBackTime);
		}else{
			return "";
		}
	}
	public Date getEndBackTime() {
		return endBackTime;
	}
	public void setEndBackTime(Date endBackTime) {
		this.endBackTime = endBackTime;
	}
	public String getStartReturnTimeStr(){
		if(startReturnTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(startReturnTime);
		}else{
			return "";
		}
	}
	public String getEndReturnTimeStr(){
		if(endReturnTime!=null){
			return new SimpleDateFormat("yyyy-MM-dd").format(endReturnTime);
		}else{
			return "";
		}
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
