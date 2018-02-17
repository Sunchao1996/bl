package com.bl.book.beans.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @功能描述 借阅信息实体类
 * @author chao
 *
 */
public class BorrowInfo {
	private Integer id;//借阅记录的主键
	private String borrowId;//借阅记录编号，由UUID生成，
	private String readerId;//读者编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date borrowTime;//借书时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date backTime;//应还时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnTime;//还书时间
	private Integer borrowStatus;//记录的状态        0表示未还           1表示已还
	private String bookSeat;//图书的位置
	private String bookBarCode;//图书的条形码

	//辅助字段
	private String bookISBN;//图书ISBN
	private String bookTitle;//图书标题
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
	private Double bookPrice;//图书标题

	//获取逾期价格
	public String getPriceMore(){
		if(returnTime == null){
			return "0";
		}
		int flag = returnTime.compareTo(backTime);
		if(flag>0){
			return bookPrice.toString();
		}else{
			return "0";
		}
	}
	public String getDateNow(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public String getBorrowTime_text() {
		return sdf.format(borrowTime);
	}
	public String getBackTime_text() {
		return sdf.format(backTime);
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public Date getBackTime() {
		return backTime;
	}
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
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
		return "BorrowInfo [id=" + id + ", borrowId=" + borrowId + ", readerId=" + readerId + ", borrowTime="
				+ borrowTime + ", backTime=" + backTime + ", returnTime=" + returnTime + ", borrowStatus="
				+ borrowStatus + ", bookSeat=" + bookSeat + ", bookBarCode=" + bookBarCode + "]";
	}
	
}
