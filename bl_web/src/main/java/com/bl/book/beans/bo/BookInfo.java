package com.bl.book.beans.bo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 图书基本信息实体类
 * @author chao
 *
 */
public class BookInfo {
	private String bookBarCode;// '图书的条形码',
	private String bookISBN   ;// '图书的ISBN码',
	private String bookTitle  ;// '图书的标题',
	private String bookAuthor ;// '图书的作者',
	private String bookPress  ;// '图书的出版社',
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bookPublish  ;//'图书的出版时间',
	private Integer bookPrintCount;//'图书的印刷次数',
	private String bookCategoryId;//'图书的类别码',
	private String bookComment;//'图书的备注',
	private Integer bookCount ;//'存在多少本与这本书一样的数，最少是1',
	private Double bookPrice;
	
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	//辅助字段
	private String bookCategory_text;//类型信息
	private String bookPublish_text;//日期字段显示
	private List<BookPrimary> bookSeatList;//图书所有的位置
	
	
	
	public List<BookPrimary> getBookSeatList() {
		return bookSeatList;
	}
	public void setBookSeatList(List<BookPrimary> bookSeatList) {
		this.bookSeatList = bookSeatList;
	}
	public String getBookPublish_text() {
		return bookPublish_text;
	}
	public void setBookPublish_text(String bookPublish_text) {
		this.bookPublish_text = bookPublish_text;
	}
	public String getBookCategory_text() {
		return bookCategory_text;
	}
	public void setBookCategory_text(String bookCategory_text) {
		this.bookCategory_text = bookCategory_text;
	}
	public String getBookBarCode() {
		return bookBarCode;
	}
	public void setBookBarCode(String bookBarCode) {
		this.bookBarCode = bookBarCode;
	}
	public String getBookISBN() {
		return bookISBN;
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
	public Date getBookPublish() {
		return bookPublish;
	}
	public void setBookPublish(Date bookPublish) {
		this.bookPublish = bookPublish;
	}
	public Integer getBookPrintCount() {
		return bookPrintCount;
	}
	public void setBookPrintCount(Integer bookPrintCount) {
		this.bookPrintCount = bookPrintCount;
	}
	public String getBookCategoryId() {
		return bookCategoryId;
	}
	public void setBookCategoryId(String bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}
	public String getBookComment() {
		return bookComment;
	}
	public void setBookComment(String bookComment) {
		this.bookComment = bookComment;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	@Override
	public String toString() {
		return "BookInfo [bookBarCode=" + bookBarCode + ", bookISBN=" + bookISBN + ", bookTitle=" + bookTitle
				+ ", bookAuthor=" + bookAuthor + ", bookPress=" + bookPress + ", bookPublish=" + bookPublish
				+ ", bookPrintCount=" + bookPrintCount + ", bookCategoryId=" + bookCategoryId + ", bookComment="
				+ bookComment + ", bookCount=" + bookCount + "]";
	}
	
}
