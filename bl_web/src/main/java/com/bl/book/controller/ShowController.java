package com.bl.book.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.service.ShowService;

/**
 * @功能描述 展示报表的处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("show")
public class ShowController {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ShowService service;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping("index.do")
	public String index(){
		return "borrow/show/index";
	}
	@RequestMapping("list.do")
	public String list(){
		return "borrow/show/list";
	}
	/**
	 * @功能描述 生成报表信息集合
	 * @return
	 */
	@RequestMapping("query.do")
	@ResponseBody
	public Object query(HttpServletRequest request){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		String startTime = request.getParameter("startBorrowTime");
		String endTime = request.getParameter("endBorrowTime");
		Map<String,Object> condMap = new HashMap<String,Object>();
		try {
			if(!"".equals(startTime) && !"".equals(endTime)){
				condMap.put("startBorrowTime", sdf.parse(startTime));
				condMap.put("endBorrowTime", sdf.parse(endTime));
			}else if(!"".equals(startTime) && "".equals(endTime)){
				condMap.put("startBorrowTime", sdf.parse(startTime));
				condMap.put("endBorrowTime", null);
			}else if("".equals(startTime) && !"".equals(endTime)){
				condMap.put("startBorrowTime", null);
				condMap.put("endBorrowTime", sdf.parse(endTime));
			}else{
				condMap.put("startBorrowTime", null);
				condMap.put("endBorrowTime", null);				
			}
			jsonMap.put("success", true);
			jsonMap.put("dataList", service.queryList(condMap));
		} catch (ParseException e) {
			logger.error("生成报表错误");
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
}
