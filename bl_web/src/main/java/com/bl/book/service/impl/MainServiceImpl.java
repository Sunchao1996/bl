package com.bl.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.Notice;
import com.bl.book.beans.bo.User;
import com.bl.book.dao.BookDao;
import com.bl.book.dao.UserDao;
import com.bl.book.service.MainService;
/**
 * @功能描述 主业务的service接口实现类
 * @author chao
 *
 */
@Service
public class MainServiceImpl implements MainService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BookDao bookDao;
	@Override
	public User login(User user) {		
		return userDao.findByUser(user);
	}

	@Override
	public List<BookInfo> getBookIntoTime(int i) {
		return bookDao.getBookIntoTime(i);
	}

	@Override
	public List<Notice> getNotice(int i) {
		return bookDao.getNotice(i);
	}
	@Override
	public List<Notice> getNoticeById(int i) {
		return bookDao.getNoticeById(i);
	}

}
