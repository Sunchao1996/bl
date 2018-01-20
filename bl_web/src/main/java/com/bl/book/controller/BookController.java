package com.bl.book.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bl.book.beans.bo.BookCategory;
import com.bl.book.beans.bo.BookInfo;
import com.bl.book.beans.bo.BookSeat;
import com.bl.book.beans.vo.BookCategoryPage;
import com.bl.book.condition.child.BookCond;
import com.bl.book.service.BookService;
/**
 * @功能描述 图书管理的处理器
 * @author chao
 *
 */
@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	private BookService bookService;
	//扩充书架
	@RequestMapping("extendBookshelves.do")
	@ResponseBody
	public Object extendBookshelves(MultipartFile bookshelves,HttpServletRequest request){
		int result = bookService.extendBookshelves(bookshelves,request);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(result > 0){
			jsonMap.put("success", true);
		}else{
			jsonMap.put("success", false);			
		}
		return jsonMap;
	}
	//书架初始化
	@RequestMapping("addBookshelves.do")
	@ResponseBody
	public Object addBookshelves(MultipartFile bookshelves,HttpServletRequest request){
		int result = bookService.addBookshelves(bookshelves,request);
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		if(result > 0){
			jsonMap.put("success", true);
		}else{
			jsonMap.put("success", false);			
		}
		return jsonMap;
	}
	
	//通过表单添加单个图书类别
	@RequestMapping("addBookCategory.do")
	@ResponseBody
	public Object addBookCategory(BookCategory bookCategory){
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			bookService.addBookCategory(bookCategory);
			jsonMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonMap;
		
	}
	//通过文件添加图书类别
	@RequestMapping("addBookCategorys.do")
	@ResponseBody
	public Object addBookCategorys(MultipartFile bookCategorys,HttpServletRequest request){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			bookService.addBookCategorys(bookCategorys,request);
			jsonMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonMap;
	}
	/**
	 * 修改之后
	 * 请求一个页面（容器），由这个页面中发送请求，之后的数据就会在这个页面中显示
	 * @return
	 */
	//查询所有的图书类别
	//TODO 2发送到了这里，请求一个页面
	@RequestMapping("queryBookCategory.do")
	public ModelAndView queryBookCategory(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/bookManage/bookCategory/book_category_query.jsp");
		return mv;
	}
	//查询所有的图书类别
	//TODO 4从这里请求数据，请求的数据将会在发送请求的页面进行展示，跳转到进行展示的页面，正常展示只不过这个展示的过程都在一个之前发出请求的页面中
	@RequestMapping("bookCategoryList.do")
	public ModelAndView bookCategoryList(BookCategoryPage page){
		ModelAndView mv = new ModelAndView();
		if(page.getPageNo() == null){
			page.setPageNo(1);
		}
		if(page.getPageSize() == null || page.getPageSize() <= 0){
			page.setPageSize(6);
		}
		List<BookCategory> bookCategoryList = bookService.queryBookCategory(page);
		mv.addObject("page", page);
		mv.addObject("bookCategoryList", bookCategoryList);
		mv.setViewName("forward:/bookManage/bookCategory/book_category_list.jsp");
		return mv;
	}
	/**
	 * @功能描述 跳转到添加页面
	 */
	@RequestMapping("insertIndex.do")
	public String insertIndex(){
		return "book/insert_index";
	}
	@RequestMapping("/toinsert.htm")
	public String toinsert(Model model){
		System.out.println("toinsert");
		List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
		model.addAttribute("bookCategoryList", bookCategoryList);
		return "book/insert";
	}
	
	/**
	 * @功能描述 添加图书
	 * @param book
	 * @return
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Object insert(BookInfo book){
		Map<String,Object> jsonMap = new HashMap<>();
		
		int count = bookService.insert(book);
		if(count == -1){
			jsonMap.put("success", "full");
		}else if(count < 3){
			jsonMap.put("success", false);
		}else{
			jsonMap.put("success", true);
		}		
		return jsonMap;
	}
	/**
	 * @功能描述 跳转页面
	 * @param book
	 * @return
	 */
	@RequestMapping("index.do")
	public String index(){		
		return "book/index";
	}
	@RequestMapping("list.do")
	public String list(Model model){	
		List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
		model.addAttribute("bookCategoryList", bookCategoryList);
		return "book/list";
	}
	/**
	 * @功能描述 跳转页面
	 * @param book
	 * @return
	 */
	@RequestMapping("getByPage.do")
	@ResponseBody
	public Object getByPage(BookCond cond){	
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("dataList", bookService.getByPage(cond));
		jsonMap.put("total", cond.getTotal());
		return jsonMap;
	}
	/**
	 * @功能描述 图书下架
	 * @param book
	 * @return
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Object delete(String[] ids){	
		Map<String,Object> jsonMap = new HashMap<>();
		try {
			bookService.delete(ids);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 图书基本信息修改跳转页面
	 * @param book
	 * @return
	 */
	@RequestMapping("toupdate.do")
	public String toupdate(String id,Model model){
		BookInfo bookInfo = bookService.findById(id);
		List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
		model.addAttribute("bookCategoryList", bookCategoryList);
		model.addAttribute("bookInfo", bookInfo);
		return "book/update";
	}
	/**
	 * @功能描述 供借书登记使用的类别查看,二级联动
	 * @param book
	 * @return
	 */
	@RequestMapping("showCategory.do")
	@ResponseBody
	public Object showCategory(){
		Map<String,Object> jsonMap = new HashMap<>();
		List<BookCategory> list = bookService.queryBookCategoryAll();
		List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
		for (BookCategory bookCategory : list) {
			if(bookCategory.getCategoryCount() > 0){
				bookCategoryList.add(bookCategory);
			}
		}
		jsonMap.put("bookCategoryList", bookCategoryList);
		return jsonMap;
	}
	/**
	 * @功能描述 供借书登记使用的位置查看,二级联动
	 * @param book
	 * @return
	 */
	@RequestMapping("getSeatById.do")
	@ResponseBody
	public Object getSeatById(String categoryId){
		Map<String,Object> jsonMap = new HashMap<>();
		List<BookSeat> bookSeat = bookService.getSeatById(categoryId);
		jsonMap.put("bookSeatList", bookSeat);
		return jsonMap;
	}
	/**
	 * @功能描述 图书基本信息修改跳转页面
	 * @param book
	 * @return
	 */
	@RequestMapping("update.do")
	@ResponseBody
	public Object update(BookInfo book){
		Map<String,Object> jsonMap = new HashMap<>();
		try {
			bookService.update(book);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonMap;
	}
	/**
	 * @功能描述 显示详情页面跳转
	 */
	@RequestMapping("detail.do")
	public String detail(String id,Model model){
		BookInfo bookInfo = bookService.detail(id);
		model.addAttribute("book", bookInfo);
		return "book/detail";
	}
	/**
	 * @功能描述 根据图书位置获取图书信息
	 */
	@RequestMapping("queryBookBySeat.do")
	@ResponseBody
	public Object queryBookBySeat(String seat){
		Map<String,Object> jsonMap = new HashMap<>();
		jsonMap.put("bookInfo", bookService.queryBookBySeat(seat));
		return jsonMap;
	}
}
