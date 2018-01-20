package com.bl.book.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.BorrowInfo;
import com.bl.book.condition.child.BorrowInfoCond;
import com.bl.book.dao.BackDao;
import com.bl.book.dao.BookDao;
import com.bl.book.service.BackService;

/**
 * @功能描述 还书登记的service
 * @author chao
 *
 */
@Service
public class BackServiceImpl implements BackService {

	@Autowired
	private BackDao dao;//注入dao
	@Autowired
	private BookDao bookDao;//用来辅助填写显示字段
	
	private Log logger = LogFactory.getLog(getClass());//日志

	@Override
	public List<BorrowInfo> queryByPage(BorrowInfoCond cond) {
		
		
		int total = dao.pageCount(cond);
		logger.info(cond.getReaderId()+"图书借阅条数:"+total);
		cond.setTotal(total);
		int startLine = (cond.getPageNo() - 1)*cond.getPageSize();
		cond.setStartLine(startLine);
		List<BorrowInfo> list = dao.queryByPage(cond);
		for (BorrowInfo borrowInfo : list) {
			BookInfo bookInfo = bookDao.findByIdBI(borrowInfo.getBookBarCode());
			borrowInfo.setBookISBN(bookInfo.getBookISBN());
			borrowInfo.setBookTitle(bookInfo.getBookTitle());
			borrowInfo.setBookPrice(bookInfo.getBookPrice());
		}
		return list;
	}

	@Override
	public int update(String[] ids) {
		int count = 0;
		Date returnTime = new Date();
		BorrowInfo bi = new BorrowInfo();
		for (String id : ids) {
			bi.setBorrowId(id);
			bi.setReturnTime(returnTime);
			bi.setBorrowStatus(1);
			count += dao.update(bi);
		}
		return count;
	}

	@Override
	public int updateLose(String[] ids) {
		int count = update(ids);
		BorrowInfo bi = new BorrowInfo();
		for (String id : ids) {
			bi.setBorrowId(id);			
			count += dao.updateLose(bi);
			count += dao.updateSeat(bi);
			count += dao.updateCategory(bi);
			count += dao.deleteBookP(bi);
		}
		return count;
	}

}
