package com.bl.book.controller;

import java.awt.print.Book;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bl.book.condition.child.BorrowInfoCond;
import com.bl.book.util.StringUtil;
import com.bl.core.pub.PubConfig;
import com.bl.util.code.GlobalCode;
import com.bl.util.global.GlobalConst;
import com.bl.util.page.PageNavigate;
import com.bl.util.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.Reader;
import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.BookCardCond;
import com.bl.book.service.BookCardService;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chao
 * @功能描述 图书卡的处理器类
 */
@Controller
@RequestMapping("book")
public class BookCardController {
    @Autowired
    private BookCardService service;//注入service
    @Autowired
    private PubConfig pubConfig;

    @RequestMapping("list.htm")
    public String list() {
        return "book_card/list";
    }

    @RequestMapping("index.htm")
    public String index() {
        return "book_card/index";
    }

    @RequestMapping("/bookcard/toinsert.htm")
    public String toinsert() {
        return "book_card/insert";
    }

    @RequestMapping("/bookcard/toAdd.htm")
    public String toAdd(HttpServletRequest request) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/book/bookcard/toAdd.htm");
        return "book/bookcard/add";
    }

    @RequestMapping("/bookcard/updateIndex.htm")
    public String updateIndex(HttpServletRequest request) {
        request.setAttribute("backUrl", pubConfig.getDynamicServer() + "/book/bookcard/updateIndex.htm");
        return "book/bookcard/updateIndex";
    }

    @RequestMapping("toupdate.htm")
    public String toupdate() {
        return "book_card/update";
    }

    /**
     * @功能描述 开通图书卡
     */
    @RequestMapping("/bookcard/add.htm")
    public Object insert(BookCard bookCard) {
        Map jsonMap = new HashMap<>();
        bookCard.setRegTime(new Date());
        bookCard.setCardStatusId(1);
        try {
            service.insert(bookCard);
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        }

    }

    /**
     * @功能描述 分页查询图书卡
     */
    @RequestMapping("/bookcard/index.htm")
    public ModelAndView getByPage(BookCardCond cond, Integer pageIndex) {
        if (cond.getPageSize() == null) {
            cond.setPageSize(GlobalConst.PAGESIZE);
        }
        cond.setPageNo(pageIndex);
        if (cond.getPageNo() == null) {
            cond.setPageNo(1);
        }
        ModelAndView mv = new ModelAndView();
        String url = createUrl(cond);
        List<BookCard> list = service.getByPage(cond);
        mv.addObject("list", list);
        PageNavigate pageNavigate = new PageNavigate(url, cond.getPageNo(), cond.getTotal());// 定义分页对象
        mv.addObject("pageNavigate", pageNavigate);// 设置分页的变量
        mv.addObject("vo", cond);// 设置分页的变量
        mv.addObject("backUrl", StringUtil.encodeUrl(url));// 设置分页的变量
        mv.setViewName("/book/bookcard/index");
        return mv;
    }

    public String createUrl(BookCardCond cond) {
        String url = pubConfig.getDynamicServer() + "/book/bookcard/index.htm?";
        if (StringUtil.isNotNullOrEmpty(cond.getBookCardId())) {
            url += "&bookCardId=" + cond.getBookCardId();
        }
        if (cond.getCardStatusId() != -1) {
            url += "&cardStatusId=" + cond.getCardStatusId();
        }
        if (cond.getStartTime() != null) {
            url += "&startTime=" + cond.getStartTime();
        }
        if (cond.getEndTime() != null) {
            url += "&endTime=" + cond.getEndTime();
        }
        return url;
    }

    /**
     * @功能描述 禁用图书卡
     */
    @RequestMapping("/bookcard/delete.htm")
    public String delete(String id) {
        try {
            service.delete(id);
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        }
    }

    /**
     * @功能描述 读者挂失图书卡
     */
    @RequestMapping("/bookcard/update.htm")
    public Object update(Reader reader, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserSession u = (UserSession) session.getAttribute("userSession");
        reader.setReaderId(String.valueOf(u.getUserId()));
        Map<String, Object> jsonMap = new HashMap<>();
        int count = service.update(reader);
        if (count <= 0)
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        else
            return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
    }
}
