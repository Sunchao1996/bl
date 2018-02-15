package com.bl.book.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bl.core.pub.PubConfig;
import com.bl.util.code.GlobalCode;
import com.bl.util.json.JsonUtil;
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
    @ResponseBody
    public String addBookCategory(BookCategory bookCategory) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try {
            bookService.addBookCategory(bookCategory);
            jsonMap.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMap.put("success", false);
        }
        return JsonUtil.toStr(jsonMap);

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
    @RequestMapping("bookCategoryList.do")
    public ModelAndView bookCategoryList(BookCategoryPage page) {
        ModelAndView mv = new ModelAndView();
        if (page.getPageNo() == null) {
            page.setPageNo(1);
        }
        if (page.getPageSize() == null || page.getPageSize() <= 0) {
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
    @RequestMapping("/books/toAdd.htm")
    public String insertIndex(HttpServletRequest request, Model model) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/book/books/toAdd.htm");
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
     * @param model
     * @return
     */
    @RequestMapping("/books/list.htm")
    public String list(Model model) {
        List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
        model.addAttribute("bookCategoryList", bookCategoryList);
        return "book/books/list";
    }

    /**
     * @param book
     * @return
     * @功能描述 跳转页面
     */
    @RequestMapping("getByPage.do")
    @ResponseBody
    public Object getByPage(BookCond cond) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataList", bookService.getByPage(cond));
        jsonMap.put("total", cond.getTotal());
        return jsonMap;
    }

    /**
     * @param book
     * @return
     * @功能描述 图书下架
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public Object delete(String[] ids) {
        Map<String, Object> jsonMap = new HashMap<>();
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
     * @param book
     * @return
     * @功能描述 图书基本信息修改跳转页面
     */
    @RequestMapping("toupdate.do")
    public String toupdate(String id, Model model) {
        BookInfo bookInfo = bookService.findById(id);
        List<BookCategory> bookCategoryList = bookService.queryBookCategoryAll();
        model.addAttribute("bookCategoryList", bookCategoryList);
        model.addAttribute("bookInfo", bookInfo);
        return "book/update";
    }

    /**
     * @param book
     * @return
     * @功能描述 供借书登记使用的类别查看, 二级联动
     */
    @RequestMapping("showCategory.do")
    @ResponseBody
    public Object showCategory() {
        Map<String, Object> jsonMap = new HashMap<>();
        List<BookCategory> list = bookService.queryBookCategoryAll();
        List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
        for (BookCategory bookCategory : list) {
            if (bookCategory.getCategoryCount() > 0) {
                bookCategoryList.add(bookCategory);
            }
        }
        jsonMap.put("bookCategoryList", bookCategoryList);
        return jsonMap;
    }

    /**
     * @param book
     * @return
     * @功能描述 供借书登记使用的位置查看, 二级联动
     */
    @RequestMapping("getSeatById.do")
    @ResponseBody
    public Object getSeatById(String categoryId) {
        Map<String, Object> jsonMap = new HashMap<>();
        List<BookSeat> bookSeat = bookService.getSeatById(categoryId);
        jsonMap.put("bookSeatList", bookSeat);
        return jsonMap;
    }

    /**
     * @param book
     * @return
     * @功能描述 图书基本信息修改跳转页面
     */
    @RequestMapping("update.do")
    @ResponseBody
    public Object update(BookInfo book) {
        Map<String, Object> jsonMap = new HashMap<>();
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
    public String detail(String id, Model model) {
        BookInfo bookInfo = bookService.detail(id);
        model.addAttribute("book", bookInfo);
        return "book/detail";
    }

    /**
     * @功能描述 根据图书位置获取图书信息
     */
    @RequestMapping("queryBookBySeat.do")
    @ResponseBody
    public Object queryBookBySeat(String seat) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("bookInfo", bookService.queryBookBySeat(seat));
        return jsonMap;
    }
}
