package com.bl.book.dao;

import java.util.List;

import com.bl.book.beans.bo.BookCategory;
import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.BookPrimary;
import com.bl.book.beans.bo.BookSeat;
import com.bl.book.beans.bo.BookStatus;
import com.bl.book.beans.bo.Notice;
import com.bl.book.beans.vo.BookCategoryPage;
import com.bl.book.condition.child.BookCond;

public interface BookDao {
	//添加单个图书类别
	public int insertBookCategory(BookCategory bookCategory);
	//添加多个图书类别
	public int insertBookCategorys(List<BookCategory> bookCategoryList);
	//统计图书类别个数
	public int selectBookCategoryCount();
	//分页查询图书类别
	public List<BookCategory> selectBookCategory(BookCategoryPage page);
	//添加书架中的位置
	public int insertBookSeat(List<BookSeat> bookSeat);
	//查询出所有的图书类别
	public List<BookCategory> selectBookCategorys();
	/**
	 * @功能描述 获取所有图书状态信息
	 */
	public List<BookStatus> queryBookStatus();
	/**
	 * @功能描述 根据条件统计图书个数
	 */
	public int queryBookCount(BookCond cond);
	/**
	 * @功能描述 根据图书类别查找出一个空余位置
	 */
	public BookSeat queryBookSeat(String bookCategory);
	/**
	 * @功能描述 添加图书
	 */
	public int insertBook(BookInfo book);
	/**
	 * @功能描述 更新图书数量
	 */
	public int updateBookCount(BookInfo book);
	/**
	 * @功能描述 更新图书位置状态
	 */
	public int updateBookSeat(BookSeat bookSeat);
	/**
	 * @功能描述 添加图书主信息
	 */
	public int insertBP(BookPrimary bookP);
	/**
	 * @功能描述 查询所有图书类别
	 */
	public List<BookCategory> queryBookCategoryAll();
	/**
	 * @功能描述 查询出所要添加的图书所属类别
	 */
	public BookCategory findByIdBC(BookCategory bc);
	/**
	 * @功能描述 更新所属类别的图书数量
	 */
	public void updateBCCount(BookCategory bc);
	/**
	 * @功能描述 分页查询
	 */
	public List<BookInfo> getByPage(BookCond cond);
	/**
	 * @功能描述 获取位置
	 */
	public List<String> getBookSeat(String id);
	/**
	 * @功能描述 删除主表信息
	 */
	public void deleteBP(BookPrimary bp);
	/**
	 * @功能描述 满足条件的分页数据数量
	 */
	public int queryCount(BookCond cond);
	/**
	 * @功能描述 根据id查询图书基本信息
	 */
	public BookInfo findByIdBI(String id);
	/**
	 * @功能描述 更新修改之后的内容
	 */
	public void update(BookInfo book);
	/**
	 * @功能描述 根据条形码去找是否存在位置
	 */
	public List<BookPrimary> findBP(BookPrimary bp);
	/**
	 * @功能描述 二级联动
	 */
	public List<BookSeat> getSeatById(String categoryId);
	/**
	 * @功能描述 根据图书位置获取图书信息
	 */
	public BookInfo queryBookBySeat(String seat);
	/**
	 * @功能描述 图书下架时更新图书类别表中图书数量
	 */
	public int updateBookCategory(BookCategory bc);
	/**
	 * @功能描述 获取图书最新的入馆时间
	 */
	public List<BookInfo> getBookIntoTime(int i);
	/**
	 * @功能描述 获取公告
	 */
	public List<Notice> getNotice(int i);
	/**
	 * @功能描述 根据id获取公告
	 */
	public List<Notice> getNoticeById(int i);
	
	
}
