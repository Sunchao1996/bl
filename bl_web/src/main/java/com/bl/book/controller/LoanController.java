package com.bl.book.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.service.LoanService;

/**
 * @功能描述 借书登记处理器
 * @author chao
 *
 */
@Controller
@RequestMapping("loan")
public class LoanController {
	@Autowired
	private LoanService service;//注入登记service
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@RequestMapping("index.do")
	public String index(){
		return "borrow/loan/index";
	}
	@RequestMapping("list.do")
	public String list(){
		return "borrow/loan/list";
	}
	/**
	 * @功能描述 对借书记录进行添加
	 */
	@RequestMapping("register.do")
	@ResponseBody
	public Object register(String[] ids){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Date date = new Date();
		Long time = date.getTime();
		Long afterTime = (long)(4*30*24*60*60)*(long)1000;
		Date afterDate = new Date(time+afterTime);
		int count = service.register(ids);
		if(count > 0){
			jsonMap.put("success", true);
			jsonMap.put("afterTime",sdf.format(afterDate));
		}else if(count == -1){
			jsonMap.put("success", false);
			jsonMap.put("error", true);
		}else{
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
}
