package com.bl.book.condition.child;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bl.book.condition.BaseCondition;
/**
 * @功能描述 图书查询的条件实体类
 * @author chao
 *
 */			
public class BookCond extends BaseCondition {
	private String bookBarCode;//图书主键条形码           
	private String	bookTitle;     //图书的标题, 
	private String bookAuthor;     //图书的作者, 
	private String bookPress;      //图书的出版社,
	private String	bookCategoryId;//图书的类别码,
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;//出版时间范围

	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getBookBarCode() {
		return bookBarCode;
	}

	public void setBookBarCode(String bookBarCode) {
		this.bookBarCode = bookBarCode;
	}

	

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public String getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(String bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	
}
