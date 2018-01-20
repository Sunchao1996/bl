package com.bl.book.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.Reader;
import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.ReaderCond;
import com.bl.book.dao.BookCardDao;
import com.bl.book.dao.ReaderDao;
import com.bl.book.dao.UserDao;
import com.bl.book.service.ReaderService;
import com.bl.book.util.StatusUtil;

/**
 * @功能描述 读者管理的service接口实现类
 * @author chao
 *
 */
@Service
public class ReaderServicImpl implements ReaderService {
	@Autowired
	private ReaderDao dao;//注入读者dao
	@Autowired
	private UserDao userDao;//注入用户dao
	
	@Autowired
	private BookCardDao bookCardDao;//注入图书证dao
	@Autowired
	private StatusUtil status;//注入状态工具类
	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public int insert(Reader reader) {
		
		reader.setReaderId(reader.getBookCardId());
		reader.setReaderStatusId(1);
		logger.info(reader.getReaderCardId());
		return dao.insert(reader);
	}

	@Override
	public List<Reader> getByPage(ReaderCond cond) {
		int total = dao.queryCount(cond);
		cond.setTotal(total);
		logger.info("读者总记录数:"+total);
		int startLine = (cond.getPageNo()-1) * cond.getPageSize();
		cond.setStartLine(startLine);
		List<Reader> list = dao.getByPage(cond);
		Map<Integer,String> map = status.getReaderMap();
		Map<Integer,String> mapBookC = status.getBookCardMap();
		for (Reader reader : list) {
			reader.setReaderStatus(map.get(reader.getReaderStatusId()));
			BookCard bookCard = bookCardDao.findById(reader.getBookCardId());
			reader.setBookCardStatus(mapBookC.get(bookCard.getCardStatusId()));
			logger.info(bookCard);
		}
		logger.info("读者总记录数:"+list);
		return list;
	}

	@Override
	public void repwd(String readerId) {
		Reader reader = new Reader();
		reader.setReaderId(readerId);
		reader = dao.findById(reader);
		String newPwd = reader.getReaderCardId().substring(reader.getReaderCardId().length()-6);
		User user = new User();
		user.setUserCategory(2);
		user.setUserId(reader.getBookCardId());
		user.setUserPassword(newPwd);
		userDao.updatePwd(user);
	}

	@Override
	public void delete(String[] ids) {
		for (String readerId : ids) {	
			Reader reader  = new Reader();
			reader.setReaderId(readerId);
			reader.setReaderStatusId(0);
			dao.updateStatus(reader);
			reader = dao.findById(reader);
			BookCard bookCard = bookCardDao.findById(reader.getBookCardId());
			bookCard.setEndTime(new Date());
			bookCardDao.delete(bookCard);
		}
	}
	@Override
	public void recover(String[] ids) {
		for (String readerId : ids) {	
			Reader reader  = new Reader();
			reader.setReaderId(readerId);
			reader.setReaderStatusId(1);
			dao.updateStatus(reader);
		}
	}

	@Override
	public List<Reader> getByS(Reader reader) {
		return dao.getByS(reader);
	}

}
