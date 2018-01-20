package com.bl.book.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.bo.BorrowInfo;
import com.bl.book.dao.LoanDao;
import com.bl.book.dao.ReaderDao;
import com.bl.book.service.LoanService;
import com.bl.book.util.UUIDGenerator;
/**
 * @功能描述 借书登记service接口实现类
 * @author chao
 *
 */
@Service
public class LoanServiceImpl implements LoanService {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Log logger = LogFactory.getLog(getClass());//日志
	@Autowired
	private LoanDao dao;
	@Autowired
	private ReaderDao readerDao;
	@Override
	public int register(String[] ids) {
		
		int count = 0;
		Date date = new Date();
		Long time = date.getTime();
		Long afterTime = (long)(4*30*24*60*60)*(long)1000;
		Date afterDate = new Date(time+afterTime);
		for (String id : ids) {
			String[] rsb = id.split("_");
			//验证读者和图书卡是否可用
			int validate = readerDao.validate(rsb[0]);
			if(validate <= 0){
				return -1;
			}
			logger.info("读者验证正确");
			logger.info(id+"应该归还时间"+afterDate.toLocaleString());
			BorrowInfo bi = new BorrowInfo();
			bi.setBorrowId(UUIDGenerator.getUUID());
			bi.setReaderId(rsb[0]);
			bi.setBorrowTime(date);
			bi.setBackTime(afterDate);
			bi.setBorrowStatus(0);
			bi.setBookSeat(rsb[1]);
			bi.setBookBarCode(rsb[2]);
			count += dao.insert(bi);
		}
		return count;
	}

}
