package com.bl.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.User;
import com.bl.book.service.UserService;

/**
 * @功能描述 所有用户公用的处理器
 * @author chao
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService service;
	
	@RequestMapping("changepwd.do")
	public String changepwd(){
		return "changepwd";
	}
	@RequestMapping("tochangepwd.do")
	public String tochangepwd(){
		return "changepwd_index";
	}
	/**
	 * @功能描述 用户保存修改的用户名
	 * @return
	 */
	@RequestMapping("updatePwd.do")
	@ResponseBody
	public Object updatePwd(User user){
		int count = service.updatePwdByUser(user);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(count > 0){
			jsonMap.put("success", true);
		}else{
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
}
