package com.bl.book.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @功能描述 用来在程序初始化的时候，将所有和状态有关的信息加载到内存
 * @author chao
 *
 */

import com.bl.book.beans.bo.BookCardStatus;
import com.bl.book.beans.bo.BookStatus;
import com.bl.book.beans.bo.ReaderStatus;
import com.bl.book.dao.BookCardDao;
import com.bl.book.dao.BookDao;
import com.bl.book.dao.ReaderDao;
@Controller
public class StatusUtil {
	//图书卡状态信息
	private Map<Integer,String> bookCardMap = new HashMap<Integer,String>();
	//读者状态信息
	private Map<Integer,String> readerMap = new HashMap<Integer,String>();
	//图书状态信息
	private Map<Integer,String> bookMap = new HashMap<Integer,String>();
	@Autowired
	private BookCardDao bookCardDao;//注入读书卡dao为了获取读书卡所有状态
	@Autowired
	private BookDao bookDao;//注入图书dao为了获取所有的图书状态信息
	@Autowired
	private ReaderDao readerDao;//注入读者dao,为了获取读者的所有状态
	//日志信息
	private Log logger = LogFactory.getLog(getClass());
	
	@PostConstruct
	//初始化加载方法
	public void initStatus(){
		List<BookCardStatus> bookCardList = bookCardDao.queryAllObj();
		for (BookCardStatus bookCardStatus : bookCardList) {
			bookCardMap.put(bookCardStatus.getCardStatusId(), bookCardStatus.getCardStatus());
		}		
		logger.info("图书卡状态:"+bookCardMap);
		
		List<ReaderStatus> readerList = readerDao.queryAllStatus();
		for (ReaderStatus readerStatus : readerList) {
			readerMap.put(readerStatus.getReaderStatusId(), readerStatus.getReaderStatus());
		}		
		logger.info("读者状态:"+readerMap);
		
		List<BookStatus> bookList = bookDao.queryBookStatus();
		for (BookStatus bookStatus : bookList) {
			bookMap.put(bookStatus.getBookStatusId(), bookStatus.getBookStatus());
		}
		logger.info("图书状态:"+bookMap);
		
	}

	public Map<Integer, String> getBookCardMap() {
		return bookCardMap;
	}

	public Map<Integer, String> getReaderMap() {
		return readerMap;
	}

	public Map<Integer, String> getBookMap() {
		return bookMap;
	}
	
	
}
