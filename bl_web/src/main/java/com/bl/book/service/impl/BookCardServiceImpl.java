package com.bl.book.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.Reader;
import com.bl.book.condition.child.BookCardCond;
import com.bl.book.condition.child.ReaderCond;
import com.bl.book.dao.BookCardDao;
import com.bl.book.dao.ReaderDao;
import com.bl.book.service.BookCardService;
import com.bl.book.util.StatusUtil;
/**
 * @功能描述 图书卡的service接口实现类
 * @author chao
 *
 */
@Service

public class BookCardServiceImpl implements BookCardService{
	@Autowired
	private BookCardDao dao;
	@Autowired
	private StatusUtil status;
	@Autowired
	private ReaderDao readerDao;//注入读者dao
	
	private Log logger = LogFactory.getLog(getClass());
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public int insert(BookCard bc) {
		
		return dao.insert(bc);
	}

	@Override
	public List<BookCard> getByPage(BookCardCond cond) {
		int total = dao.queryCount(cond);
		cond.setTotal(total);
		logger.info("查询到满足条件的图书卡记录数:"+total);
		int startLine = (cond.getPageNo()-1) * cond.getPageSize();
		cond.setStartLine(startLine);
		List<BookCard> list = dao.queryByPage(cond);
		Map<Integer,String> map = status.getBookCardMap();
		for (BookCard bookCard : list) {
			if(bookCard.getEndTime() != null){
				bookCard.setEndTime_text(sdf.format(bookCard.getEndTime()));
			}else{
				bookCard.setEndTime_text("至今");
			}
			bookCard.setStatus_text(map.get(bookCard.getCardStatusId()));
		}
		logger.info(list);
		return list;
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			BookCard bookCard = dao.findById(id);
			bookCard.setEndTime(new Date());
			dao.delete(bookCard);
			ReaderCond cond = new ReaderCond();
			cond.setBookCardId(bookCard.getBookCardId());
			cond.setReaderStatusId(-2);//-2表示全部状态查询
			cond.setStartLine(0);//从开始位置查
			cond.setPageSize(1);//因为每个图书卡对应一个读者,所以一张图书卡只可能找到一个用户,所以提取出一个
			List<Reader> list = readerDao.getByPage(cond);
			Reader reader = list.get(0);
			reader.setReaderStatusId(0);
			readerDao.updateStatus(reader);
			
		}
		
	}

	@Override
	public int update(Reader reader) {
		reader.setReaderStatusId(-1);
		return readerDao.updateStatus(reader);
	}

}
