package com.bl.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.condition.child.BorrowInfoCond;
import com.bl.book.service.BackService;

/**
 * @功能描述 还书的处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("/back")
public class BackController {
	@RequestMapping("/test.htm")
	@ResponseBody
	public String test(){
		return "test";
	}
	@Autowired
	private BackService service;//注入service
	
	@RequestMapping("index.do")
	public String index(){
		return "borrow/back/index";
	}
	@RequestMapping("list.do")
	public String list(){
		return "borrow/back/list";
	}
	/**
	 * @功能描述 根据图书卡卡号查询借阅信息(读者和借阅员)
	 * @return
	 */
	@RequestMapping("queryByPage.do")
	@ResponseBody
	public Object queryByPage(BorrowInfoCond bc){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
//		bc.setBorrowStatus(0);
		jsonMap.put("borrowList", service.queryByPage(bc));
		jsonMap.put("total", bc.getTotal());
		return jsonMap;
	}
	/**
	 * @功能描述 根据图书卡卡号查询借阅信息(管理员)
	 * @return
	 */
	@RequestMapping("queryByPageByA.do")
	@ResponseBody
	public Object queryByPageByA(BorrowInfoCond bc){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
//		bc.setBorrowStatus(-1);
		jsonMap.put("borrowList", service.queryByPage(bc));
		jsonMap.put("total", bc.getTotal());
		return jsonMap;
	}
	/**
	 * @功能描述 批量更新(还书)
	 * @return
	 */
	@RequestMapping("back.do")
	@ResponseBody
	public Object back(String[] ids){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			int result = service.update(ids);
			if(result > 0){
				jsonMap.put("success", true);
			}else{
				jsonMap.put("success", false);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 批量更新(丢失图书)
	 * @return
	 */
	@RequestMapping("lose.do")
	@ResponseBody
	public Object lose(String[] ids){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			int result = service.updateLose(ids);
			if(result > 0){
				jsonMap.put("success", true);
			}else{
				jsonMap.put("success", false);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
}
