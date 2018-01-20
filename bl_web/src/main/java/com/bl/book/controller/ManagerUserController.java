package com.bl.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.UserCond;
import com.bl.book.service.UserService;

/**
 * @功能描述 图书借阅员的处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("manager")
public class ManagerUserController {
	@Autowired
	private UserService userService;//注入用户service
	
	@RequestMapping("index.do")
	public String index(){
		return "user/manager/index";
	}
	@RequestMapping("list.do")
	public String list(){
		return "user/manager/list";
	}
	@RequestMapping("toinsertIndex.do")
	public String toinsertIndex(){
		return "user/manager/insert_index";
	}
	@RequestMapping("toinsert.do")
	public String toinsert(){
		return "user/manager/insert";
	}
	/**
	 * @功能描述 新增图书借阅员
	 * @return
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Object insert(User managerUser){
		Map jsonMap = new HashMap<>();
		managerUser.setUserCategory(1);//1:图书借阅员
		try {
			userService.insert(managerUser);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 删除图书借阅员
	 * @return
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Object delete(String[] ids){
		Map jsonMap = new HashMap<>();
		try {
			userService.delete(ids);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 显示所有图书借阅员信息
	 * @return
	 */
	@RequestMapping("getByPage.do")
	@ResponseBody
	public Object getByPage(UserCond cond){
		cond.setUserCategory("1");
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("dataList", userService.getByPage(cond));
		jsonMap.put("total", cond.getTotal());
		return jsonMap;
	}
	/**
	 * @功能描述 重置图书借阅员密码
	 */
	@RequestMapping("repwd.do")
	@ResponseBody
	public Object repwd(User user){
		user.setUserPassword("111111");
		user.setUserCategory(1);
		Map<String,Object> jsonMap = new HashMap<>();
		try {
			userService.updatePwd(user);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	
}
