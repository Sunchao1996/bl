package com.bl.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.Reader;
import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.BookCardCond;
import com.bl.book.service.BookCardService;

/**
 * @功能描述 图书卡的处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("book_card")
public class BookCardController {
	@Autowired
	private BookCardService service;//注入service
	
	@RequestMapping("list.do")
	public String list(){
		return "book_card/list";
	}
	@RequestMapping("index.do")
	public String index(){
		return "book_card/index";
	}
	@RequestMapping("toinsert.do")
	public String toinsert(){
		return "book_card/insert";
	}
	@RequestMapping("toinsertIndex.do")
	public String toinsertIndex(){
		return "book_card/insert_index";
	}
	@RequestMapping("updateIndex.do")
	public String updateIndex(){
		return "book_card/update_index";
	}
	@RequestMapping("toupdate.do")
	public String toupdate(){
		return "book_card/update";
	}
	/**
	 * @功能描述 开通图书卡
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Object insert(BookCard bookCard){
		Map jsonMap = new HashMap<>();
		bookCard.setRegTime(new Date());
		bookCard.setCardStatusId(1);
		try {
			service.insert(bookCard);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	/**
	 * @功能描述 分页查询图书卡
	 */
	@RequestMapping("getByPage.do")
	@ResponseBody
	public Object getByPage(BookCardCond cond){
		Map jsonMap = new HashMap<>();
		jsonMap.put("dataList", service.getByPage(cond));
		jsonMap.put("total", cond.getTotal());	
		return jsonMap;
	}
	/**
	 * @功能描述 禁用图书卡
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Object delete(String[] ids){
		Map jsonMap = new HashMap<>();
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
	 * @功能描述 读者挂失图书卡
	 */
	@RequestMapping("update.do")
	@ResponseBody
	public Object update(Reader reader,HttpServletRequest request){
		HttpSession session = request.getSession(false);
		User u = (User) session.getAttribute("user");
		reader.setReaderId(u.getUserId());
		Map<String,Object> jsonMap = new HashMap<>();
		int count = service.update(reader);
		if(count > 0){
			jsonMap.put("success", true);
		}else{
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
}
