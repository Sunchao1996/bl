package com.bl.book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bl.book.util.StringUtil;
import com.bl.core.pub.PubConfig;
import com.bl.sys.model.SysUser;
import com.bl.util.code.GlobalCode;
import com.bl.util.global.GlobalConst;
import com.bl.util.json.JsonUtil;
import com.bl.util.page.PageNavigate;
import com.bl.util.web.WebUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.HTTP;
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
 * @author chao
 * @功能描述 图书管理的处理器
 */
@Controller
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("/bookshelves/toAdd.htm")
    public String toAdd(HttpServletRequest request) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/book/bookshelves/toAdd.htm");
        return "book/bookshelves/init";
    }

    @RequestMapping("/bookshelves/toExtend.htm")
    public String toExtend(HttpServletRequest request) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/book/bookshelves/toExtend.htm");
        return "book/bookshelves/extend";
    }

    //扩充书架
    @RequestMapping("/bookshelves/extend.htm")
    public Object bookshelvesExtend(MultipartFile bookshelves, HttpServletRequest request) {
        int result = bookService.extendBookshelves(bookshelves, request);
        if (result <= 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
    }

    //书架初始化
    @RequestMapping("/bookshelves/add.htm")
    public Object bookShelvesAdd(MultipartFile bookshelves, HttpServletRequest request) {
        int result = bookService.addBookshelves(bookshelves, request);
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        if (result <= 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
    }

    /**
     * 跳转到图书类别添加页面
     *
     * @return
     */
    @RequestMapping("/books/category/toAdd.htm")
    public String toBookCategoryAdd(HttpServletRequest request) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/books/category/toAdd.htm");
        return "book/bookcategory/add";
    }

    //通过表单添加单个图书类别
    @RequestMapping("/books/category/addOne.htm")
    public void addBookCategory(BookCategory bookCategory,HttpServletResponse response) throws IOException {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            bookService.addBookCategory(bookCategory);
            jsonMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        WebUtil.out(response,new ObjectMapper().writeValueAsString(jsonMap));

    }

    //通过文件添加图书类别
    @RequestMapping("/books/category/addMore.htm")
    @ResponseBody
    public Object addBookCategorys(MultipartFile bookCategorys, HttpServletRequest request) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            bookService.addBookCategorys(bookCategorys, request);
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
     *
     * @return
     */
    //查询所有的图书类别
    //TODO 2发送到了这里，请求一个页面
    @RequestMapping("queryBookCategory.do")
    public ModelAndView queryBookCategory() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:/bookManage/bookCategory/book_category_query.jsp");
        return mv;
    }

    //查询所有的图书类别
    //TODO 4从这里请求数据，请求的数据将会在发送请求的页面进行展示，跳转到进行展示的页面，正常展示只不过这个展示的过程都在一个之前发出请求的页面中
    @RequestMapping("/books/category/list.htm")
    public ModelAndView bookCategoryList(BookCategoryPage page,Integer pageIndex) {
        ModelAndView mv = new ModelAndView();
        page.setPageNo(pageIndex);
        if (page.getPageNo() == null) {
            page.setPageNo(1);
        }
        if (page.getPageSize() == null || page.getPageSize() <= 0) {
            page.setPageSize(GlobalConst.PAGESIZE);
        }
        List<BookCategory> bookCategoryList = bookService.queryBookCategory(page);
        PageNavigate pageNavigate = new PageNavigate(pubConfig.getDynamicServer()+"/book/books/category/list.htm", page.getPageNo(), page.getBookCategorys());// 定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("page", page);
        mv.addObject("list", bookCategoryList);
        mv.setViewName("/book/bookcategory/list");
        return mv;
    }

    /**
     * @功能描述 跳转到添加页面
     */
    @RequestMapping("/books/toAdd.htm")
    public String insertIndex(HttpServletRequest request, Model model) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/book/books/toAdd.htm");
        model.addAttribute("addFlag",1);
        List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
        model.addAttribute("bookCategoryList", bookCategoryList);
        return "book/books/add";
    }

    @RequestMapping("/toinsert.htm")
    public String toinsert(Model model) {
        System.out.println("toinsert");
        List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
        model.addAttribute("bookCategoryList", bookCategoryList);
        return "book/insert";
    }

    /**
     * @param book
     * @return
     * @功能描述 添加图书
     */
    @RequestMapping("/books/add.htm")
    public String insert(BookInfo book) {
        Map<String, Object> jsonMap = new HashMap<>();

        int count = bookService.insert(book);
//        if (count == -1) {
//            jsonMap.put("success", "full");
//        } else if (count < 3) {
//            jsonMap.put("success", false);
//        } else {
//            jsonMap.put("success", true);
//        }
        if (count < 3)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
    }


    /**
     * 图书首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/books/list.htm")
    public String list(Model model) {
        List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
        model.addAttribute("bookCategorys", bookCategoryList);
        return "book/books/list";
    }

    /**
     * @param
     * @return
     * @功能描述 跳转页面
     */
    @RequestMapping("/books/getByPage.htm")
    public ModelAndView getByPage(BookCond cond, Integer pageIndex) {
        if (cond.getPageSize() == null) {
            cond.setPageSize(GlobalConst.PAGESIZE);
        }
        cond.setPageNo(pageIndex);
        if (cond.getPageNo() == null) {
            cond.setPageNo(1);
        }
        ModelAndView mv = new ModelAndView();
        String url = createUrl(cond);
        List<BookInfo> list = bookService.getByPage(cond, mv);
        mv.addObject("list", list);
        PageNavigate pageNavigate = new PageNavigate(url, cond.getPageNo(), cond.getTotal());// 定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("vo", cond);
        mv.addObject("backUrl",StringUtil.encodeUrl(url+"&pageIndex="+cond.getPageNo()));
        mv.setViewName("/book/books/list");
        return mv;
    }

    public String createUrl(BookCond cond) {
        String url = pubConfig.getDynamicServer() + "/book/books/getByPage.htm?";
        if (StringUtil.isNotNullOrEmpty(cond.getBookTitle())) {
            url += "&bookTitle=" + cond.getBookTitle();
        }
        if (StringUtil.isNotNullOrEmpty(cond.getBookAuthor())) {
            url += "&bookAuthor=" + cond.getBookAuthor();
        }
        if (StringUtil.isNotNullOrEmpty(cond.getBookPress())) {
            url += "&bookPress=" + cond.getBookPress();
        }
        if (cond.getStartTime() != null) {
            url += "&startTime=" + cond.getStartTime().toString();
        }
        if (cond.getEndTime() != null) {
            url += "&endTime=" + cond.getEndTime().toString();
        }
        return url;
    }

    /**
     * @param
     * @return
     * @功能描述 图书下架
     */
    @RequestMapping("/books/delete.htm")
    public String delete(String code) {
        int count = bookService.delete(code);
        if(count >0 ){
            return "forward:/success.htm?resultCode="+GlobalCode.OPERA_SUCCESS;
        }else{
            return "forward:/error.htm?resultCode="+GlobalCode.OPERA_FAILURE;
        }
    }

    /**
     * @param
     * @return
     * @功能描述 图书基本信息修改跳转页面
     */
    @RequestMapping("/books/toUpdate.htm")
    public String toupdate(String id,String backUrl, Model model) {
        BookInfo bookInfo = bookService.findById(id);
        List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
        model.addAttribute("updateFlag",1);
        model.addAttribute("bookCategoryList", bookCategoryList);
        model.addAttribute("book", bookInfo);
        model.addAttribute("backUrl",StringUtil.encodeUrl(backUrl));
        return "book/books/add";
    }

    /**
     * @param
     * @return
     * @功能描述 供借书登记使用的类别查看, 二级联动
     */
    @RequestMapping("/books/showCategory.htm")
    public void showCategory(HttpServletResponse response) {
        List<BookCategory> list = bookService.queryBookCategoryAll();
        List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
        for (BookCategory bookCategory : list) {
            if (bookCategory.getCategoryCount() > 0) {
                bookCategoryList.add(bookCategory);
            }
        }
        WebUtil.out(response,JsonUtil.toStr(bookCategoryList));
    }


    /**
     * @param
     * @return
     * @功能描述 供借书登记使用的位置查看, 二级联动
     */
    @RequestMapping("/books/getSeatById.htm")
    public void getSeatById(HttpServletResponse response,String categoryId) {
        List<BookSeat> bookSeat = bookService.getSeatById(categoryId);
        WebUtil.out(response,JsonUtil.toStr(bookSeat));
    }

    /**
     * @param book
     * @return
     * @功能描述 图书基本信息修改跳转页面
     */
    @RequestMapping("/books/update.htm")
    public String update(BookInfo book) {
        Map<String, Object> jsonMap = new HashMap<>();
        try {
            bookService.update(book);
            return "forward:/success.htm?resultCode="+GlobalCode.OPERA_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return "forward:/error.htm?resultCode="+GlobalCode.OPERA_FAILURE;
        }
    }

    /**
     * @功能描述 显示详情页面跳转
     */
    @RequestMapping("/books/detail.htm")
    public void detail(String id, HttpServletResponse response) {
        BookInfo bookInfo = bookService.detail(id);
        WebUtil.out(response,JsonUtil.toStr(bookInfo));
    }

    /**
     * @功能描述 根据图书位置获取图书信息
     */
    @RequestMapping("/books/queryBookBySeat.htm")
    public void queryBookBySeat(String seat,HttpServletResponse response) {
        BookInfo bookInfo =  bookService.queryBookBySeat(seat);
        WebUtil.out(response,JsonUtil.toStr(bookInfo));
    }
}
