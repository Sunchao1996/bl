package com.bl.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bl.util.code.GlobalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.Reader;
import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.ReaderCond;
import com.bl.book.dao.BookCardDao;
import com.bl.book.dao.UserDao;
import com.bl.book.service.ReaderService;
import com.bl.book.util.StatusUtil;

/**
 * @功能描述 读者处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("/book/reader")
public class ReadUserController {
	
	@Autowired
	private ReaderService service;
	
	@Autowired
	private UserDao userDao;//注入用户dao
	@Autowired
	private BookCardDao bookCardDao;

	@RequestMapping("/insert")
	public String  insert(Reader reader){
		User user = new User();
		user.setUserCategory(2);
		user.setUserId(reader.getBookCardId());
		user.setUserPassword(reader.getReaderCardId().substring(reader.getReaderCardId().length()-6));
		List<Reader> readerList = service.getByS(reader);
		if(readerList.size() > 0 ){
			BookCard card = new BookCard();
			card.setBookCardId(reader.getBookCardId());
			bookCardDao.deleteT(card);
			return "froward:/error.htm?resultCode="+ GlobalCode.OPERA_FAILURE;
		}else{
			int count = userDao.insert(user);//将读者这个用户添加到用户表
			count += service.insert(reader);
			if(count > 1){
				return "froward:/success.htm?resultCode="+ GlobalCode.OPERA_SUCCESS;
			}else{
				return "froward:/error.htm?resultCode="+ GlobalCode.OPERA_FAILURE;

			}
		}
	}

	
}
