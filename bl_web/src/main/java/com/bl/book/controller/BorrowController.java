package com.bl.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.BookCategory;
import com.bl.book.condition.child.BorrowInfoCond;
import com.bl.book.service.BackService;
import com.bl.book.service.BookService;

/**
 * @功能描述 用来处理借阅信息的处理类
 * @author chao
 *
 */
@Controller
@RequestMapping("borrow")
public class BorrowController {
	@Autowired
	private BookService bookService;//注入book的service
	@Autowired
	private BackService backService;//注入还书的service
	private Log logger = LogFactory.getLog(getClass());//日志
	
	@RequestMapping("index.htm")
	public String index(){
		return "borrow/admin/index";
	}
	@RequestMapping("list.htm")
	public String list(Model model){
		List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
		model.addAttribute("bookCategoryList", bookCategoryList);
		return "borrow/admin/list";
	}
	/**
	 *@功能描述 用来条件分页查询 
	 */
	@RequestMapping("queryByPage.htm")
	@ResponseBody
	public Object queryByPage(BorrowInfoCond cond){
		logger.info("管理员条件查询借阅信息的条件:"+cond);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("borrowList",backService.queryByPage(cond));
		jsonMap.put("total",cond.getTotal());
		return jsonMap;
	}
}
