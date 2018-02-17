package com.bl.book.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bl.util.web.WebUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.service.LoanService;

import javax.servlet.http.HttpServletResponse;

/**
 * @功能描述 借书登记处理器
 * @author chao
 *
 */
@Controller
@RequestMapping("book")
public class LoanController {
	@Autowired
	private LoanService service;//注入登记service
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@RequestMapping("/bookloan/index.htm")
	public String index(){
		return "book/loan/index";
	}
	@RequestMapping("list.do")
	public String list(){
		return "borrow/loan/list";
	}
	/**
	 * @功能描述 对借书记录进行添加
	 */
	@RequestMapping("/bookloan/register.htm")
	public void register(HttpServletResponse response, String[] ids) throws IOException {
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
		WebUtil.out(response,new ObjectMapper().writeValueAsString(jsonMap));
	}
}
