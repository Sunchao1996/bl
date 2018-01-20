package com.bl.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.Notice;
import com.bl.book.beans.bo.User;
import com.bl.book.service.MainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @功能描述 主处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("main")
public class MainController {
	@Autowired
	private MainService service;
	
	private Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping("tologin.do")
	private String tologin(){
		return "login";
	}
	@RequestMapping("reader_no.do")
	private String reader_no(){
		return "main_reader_no";
	}
	@RequestMapping("super.do")
	private String superI(){
		return "main_super";
	}
	@RequestMapping("manager.do")
	private String manager(){
		return "main_manager";
	}
	@RequestMapping("reader.do")
	private String reader(){
		return "main_reader";
	}
	/**
	 * @功能描述 用户登录验证
	 */
	@RequestMapping("login.do")
	@ResponseBody
	private Object login(User user,HttpSession session){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		User u = service.login(user);
		if(u != null){
			jsonMap.put("jump", u.getUserCategory());
			jsonMap.put("success", true);
			session.setAttribute("user", u);
		}else{
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
	/**
	 * @功能描述 退出登录
	 */
	@RequestMapping("quit.do")
	private String quit(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:tologin.do";
	}
	/**
	 * @功能描述 首页获取信息并跳转
	 */
	@RequestMapping("index.do")
	private String index(Map<String,Object> map){
		List<BookInfo> list = service.getBookIntoTime(4);
		List<Notice> noticeList = service.getNotice(7);
		logger.info(list);
		map.put("bookList", list);
		map.put("noticeList", noticeList);
		return "index/index";
	}
	/**
	 * @功能描述 根据id获取公告
	 */
	@RequestMapping("notice.do")
	private String notice(Integer id,Map<String,Object> map){
		List<Notice> noticeList = service.getNoticeById(id);
		logger.info(noticeList);
		map.put("notice", noticeList.get(0));
		return "index/notice";
	}
	
}
