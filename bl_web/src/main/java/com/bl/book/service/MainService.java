package com.bl.book.service;

import java.util.List;

import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.Notice;
import com.bl.book.beans.bo.User;

/**
 * @功能描述 主业务的service接口
 * @author chao
 *
 */
public interface MainService {
	User login(User user);
	/**
	 * @功能描述 获取图书的入馆时间信息
	 *
	 */
	List<BookInfo> getBookIntoTime(int i);
	/**
	 * @功能描述 获取公告
	 *
	 */
	List<Notice> getNotice(int i);
	/**
	 * @功能描述 根据id获取公告
	 *
	 */
	List<Notice> getNoticeById(int i);
}
