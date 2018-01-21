package com.bl.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.bl.book.beans.bo.BookCategory;
import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.BookSeat;
import com.bl.book.beans.vo.BookCategoryPage;
import com.bl.book.condition.child.BookCond;

public interface BookService {

	int addBookCategory(BookCategory bookCategory);

	int addBookCategorys(MultipartFile bookCategory, HttpServletRequest request);

	List<BookCategory> queryBookCategory(BookCategoryPage page);

	int addBookshelves(MultipartFile bookshelves, HttpServletRequest request);

	int extendBookshelves(MultipartFile bookshelves, HttpServletRequest request);
	/**
	 *@功能描述 添加图书 
	 */
	int insert(BookInfo book);
	/**
	 *@功能描述 查询所有图书类别
	 */
	List<BookCategory> queryBookCategoryAll();
	/**
	 *@功能描述 分页查询
	 */
	List<BookInfo> getByPage(BookCond cond);
	/**
	 *@功能描述 图书下架
	 */
	void delete(String[] ids);
	/**
	 *@功能描述 根据id查询图书基本信息
	 */
	BookInfo findById(String id);
	/**
	 * @功能描述 保存更新之后的数据
	 * @param book
	 */
	void update(BookInfo book);
	/**
	 * @功能描述 显示详尽页
	 * @param id
	 * @return
	 */
	BookInfo detail(String id);
	/**
	 * @功能描述 二级联动查看
	 * @param id
	 * @return
	 */
	List<BookSeat> getSeatById(String categoryId);
	/**
	 * @功能描述 根据图书位置获取图书信息
	 */
	BookInfo queryBookBySeat(String seat);

}
