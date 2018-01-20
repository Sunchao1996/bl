package com.bl.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bl.book.beans.bo.User;
import com.bl.book.service.UserService;

/**
 * @功能描述 超级管理员的处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("super")
public class SuperUserController {
	@Autowired
	private UserService service;
	
	@RequestMapping("index.do")
	public String index(){
		return "user/super/index";
	}
	@RequestMapping("repwd.do")
	public String repwd(Model model){
		
		User user = new User();
		user.setUserId("admin");
		user.setUserPassword("111111");
		user.setUserCategory(0);
		try {
			service.updatePwd(user);
			model.addAttribute("message", "重置成功,下次登录时有效");
		} catch (Exception e) {
			model.addAttribute("message", "重置失败");
			e.printStackTrace();
		}
		return "user/super/repwd";
	}
}
