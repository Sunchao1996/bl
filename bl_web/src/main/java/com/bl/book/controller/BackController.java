package com.bl.book.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bl.book.beans.bo.BorrowInfo;
import com.bl.book.util.StringUtil;
import com.bl.core.pub.PubConfig;
import com.bl.util.global.GlobalConst;
import com.bl.util.json.JsonUtil;
import com.bl.util.page.PageNavigate;
import com.bl.util.web.WebUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.condition.child.BorrowInfoCond;
import com.bl.book.service.BackService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @功能描述 还书的处理器类
 * @author chao
 *
 */
@Controller
@RequestMapping("/book/back")
public class BackController {
	@RequestMapping("/test.htm")
	@ResponseBody
	public String test(){
		return "test";
	}
	@Autowired
	private BackService service;//注入service

	@Autowired
	private PubConfig pubConfig;
	@RequestMapping("list.do")
	public String list(){
		return "borrow/back/list";
	}

	/**
	 * 验证此读者是否有未还图书
	 * @param response
	 * @param cond
	 */
	@RequestMapping("/checkBorrowInfo.htm")
	public void checkBorrowInfo(HttpServletResponse response,BorrowInfoCond cond){
		if (cond.getPageSize() == null) {
			cond.setPageSize(GlobalConst.PAGESIZE);
		}
		if (cond.getPageNo() == null) {
			cond.setPageNo(1);
		}
		List<BorrowInfo> list = service.queryByPage(cond);
		WebUtil.out(response, JsonUtil.toStr(cond.getTotal()));
	}
	/**
	 * @功能描述 根据图书卡卡号查询借阅信息(读者和借阅员)
	 * @return
	 */
	@RequestMapping("/queryByPage.htm")
	public ModelAndView queryByPage(BorrowInfoCond bc,Integer pageIndex,Integer flag){
		if (bc.getPageSize() == null) {
			bc.setPageSize(GlobalConst.PAGESIZE);
		}
		bc.setPageNo(pageIndex);
		if (bc.getPageNo() == null) {
			bc.setPageNo(1);
		}
		ModelAndView mv = new ModelAndView();
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<BorrowInfo> list = service.queryByPage(bc);
		String url = createUrl(bc);
		PageNavigate pageNavigate = new PageNavigate(url, bc.getPageNo(), bc.getTotal());// 定义分页对象
		mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
		mv.addObject("vo", bc);
		mv.addObject("list",list);
		if(flag == null || flag != 1){
			mv.setViewName("/book/borrowinfo/listByUser");
		}else{
			mv.setViewName("book/bookback/index");
		}
		return mv;
	}
	public String createUrl(BorrowInfoCond cond){
		String url = pubConfig.getDynamicServer()+"/book/back/queryByPage.htm?";
		if(StringUtil.isNotNullOrEmpty(cond.getReaderId())){
			url += "&readerId="+cond.getReaderId();
		}
		if(cond.getBorrowStatus() != -1){
			url +="&borrowStatus="+cond.getBorrowStatus();
		}
		return url;
	}
	@RequestMapping("/index.htm")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("book/bookback/index");
		return mv;
	}
	/**
	 * @功能描述 批量更新(还书)
	 * @return
	 */
	@RequestMapping("/back.htm")
	public void back(HttpServletResponse response,String[] ids) throws IOException {
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
		WebUtil.out(response,new ObjectMapper().writeValueAsString(jsonMap));
	}
	/**
	 * @功能描述 批量更新(丢失图书)
	 * @return
	 */
	@RequestMapping("/lose.htm")
	public void lose(HttpServletResponse response,String[] ids) throws IOException {
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
		WebUtil.out(response,new ObjectMapper().writeValueAsString(jsonMap));
	}
}
