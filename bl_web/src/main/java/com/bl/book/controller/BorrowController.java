package com.bl.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bl.book.util.StringUtil;
import com.bl.core.pub.PubConfig;
import com.bl.util.global.GlobalConst;
import com.bl.util.page.PageNavigate;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * @功能描述 用来处理借阅信息的处理类
 * @author chao
 *
 */
@Controller
@RequestMapping("/book/borrowinfo")
public class BorrowController {
	@Autowired
	private BookService bookService;//注入book的service
	@Autowired
	private BackService backService;//注入还书的service
	private Log logger = LogFactory.getLog(getClass());//日志
	@Autowired
	private PubConfig pubConfig;

	@RequestMapping("/index.htm")
	public String list(Model model){
		List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
		model.addAttribute("bookCategoryList", bookCategoryList);
		return "book/borrowinfo/index";
	}
	/**
	 *@功能描述 用来条件分页查询 
	 */
	@RequestMapping("/queryByPage.htm")
	public ModelAndView queryByPage(BorrowInfoCond cond, Integer pageIndex){
		ModelAndView mv = new ModelAndView();
		if (cond.getPageSize() == null) {
			cond.setPageSize(GlobalConst.PAGESIZE);
		}
		cond.setPageNo(pageIndex);
		if (cond.getPageNo() == null) {
			cond.setPageNo(1);
		}
		List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
		mv.addObject("bookCategoryList", bookCategoryList);
		logger.info("管理员条件查询借阅信息的条件:"+cond);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		String url = createUrl(cond);
		mv.addObject("list",backService.queryByPage(cond));
		PageNavigate pageNavigate = new PageNavigate(url, cond.getPageNo(), cond.getTotal());// 定义分页对象
		mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
		mv.addObject("vo", cond);// 设置分页的变量
		mv.setViewName("/book/borrowinfo/index");
		return mv;
	}
	public String createUrl(BorrowInfoCond cond){
		String url = pubConfig.getDynamicServer()+"/book/borrowinfo/queryByPage.htm?";
		if(StringUtil.isNotNullOrEmpty(cond.getReaderId())){
			url += "&readerId="+cond.getReaderId();
		}
		if(cond.getBorrowStatus() != -1){
			url +="&borrowStatus="+cond.getBorrowStatus();
		}
		if(cond.getEndBackTime() != null){
			url += "&endBackTime="+cond.getEndBackTime();
		}
		if(cond.getStartBackTime() != null){
			url += "&startBackTime="+cond.getStartBackTime();
		}
		if(cond.getEndBorrowTime()!= null){
			url += "&endBorrowTime="+cond.getEndBorrowTime();
		}
		if(cond.getStartBorrowTime() != null){
			url += "&startBorrowTime="+cond.getStartBorrowTime();
		}
		if(cond.getEndReturnTime()!= null){
			url += "&endReturnTime="+cond.getEndReturnTime();
		}
		if(cond.getStartBorrowTime() != null){
			url += "&startReturnTime="+cond.getStartBorrowTime();
		}
		if(cond.getBookCategoryId() != null){
			url += "&bookCategoryId="+cond.getBookCategoryId();
		}
		return url;
	}
}
