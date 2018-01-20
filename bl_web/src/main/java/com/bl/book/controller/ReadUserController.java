package com.bl.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("reader")
public class ReadUserController {
	
	@Autowired
	private ReaderService service;
	
	@Autowired
	private UserDao userDao;//注入用户dao
	@Autowired
	private BookCardDao bookCardDao;
	@Autowired
	private StatusUtil status;
	
	@RequestMapping("index.do")
	public String index(){
		return "user/reader/index";
	}
	@RequestMapping("list.do")
	public String list(Model model){
		model.addAttribute("readerStatus", status.getReaderMap());
		return "user/reader/list";
	}
	@RequestMapping("toinsertIndex.do")
	public String toinsertIndex(){
		return "user/reader/insert_index";
	}
	@RequestMapping("toinsert.do")
	public String toinsert(){
		return "user/reader/insert";
	}
	/**
	 * @功能描述 添加读者
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Object insert(Reader reader){
		Map jsonMap = new HashMap();
		
		User user = new User();
		user.setUserCategory(2);
		user.setUserId(reader.getBookCardId());
		user.setUserPassword(reader.getReaderCardId().substring(reader.getReaderCardId().length()-6));
		List<Reader> readerList = service.getByS(reader	);
		if(readerList.size() > 0 ){
			jsonMap.put("success", false);
			jsonMap.put("message", "一个用户只能有一个正在使用的图书卡");
			BookCard card = new BookCard();
			card.setBookCardId(reader.getBookCardId());
			bookCardDao.deleteT(card);
		}else{
			
			int count = userDao.insert(user);//将读者这个用户添加到用户表
			
			count += service.insert(reader);
			if(count > 1){
				jsonMap.put("success", true);
			}else{
				jsonMap.put("success", false);
				jsonMap.put("message", "添加失败");
			}
			
		}
		return jsonMap;
	}
	
	/**
	 * @功能描述 分页查询读者
	 */
	@RequestMapping("getByPage.do")
	@ResponseBody
	public Object getByPage(ReaderCond cond){
		Map jsonMap = new HashMap();
		jsonMap.put("dataList", service.getByPage(cond));
		jsonMap.put("total", cond.getTotal());
		return jsonMap;
	}
	/**
	 * @功能描述 重置读者密码
	 */
	@RequestMapping("repwd.do")
	@ResponseBody
	public Object repwd(String readerId){
		Map jsonMap = new HashMap();
		try {
			service.repwd(readerId);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 对读者进行销户
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Object delete(String[] ids){
		Map jsonMap = new HashMap();
		try {
			service.delete(ids);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 对读者进行恢复
	 */
	@RequestMapping("recover.do")
	@ResponseBody
	public Object recover(String[] ids){
		Map jsonMap = new HashMap();
		try {
			service.recover(ids);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	
}
