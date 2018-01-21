package com.bl.book.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bl.core.pub.PubConfig;
import com.bl.util.code.GlobalCode;
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
    @RequestMapping("getByPage.htm")
    @ResponseBody
    public Object getByPage(BookCardCond cond) {
        Map jsonMap = new HashMap<>();
        jsonMap.put("dataList", service.getByPage(cond));
        jsonMap.put("total", cond.getTotal());
        return jsonMap;
    }

    /**
     * @功能描述 禁用图书卡
     */
    @RequestMapping("delete.htm")
    @ResponseBody
    public Object delete(String[] ids) {
        Map jsonMap = new HashMap<>();
        try {
            service.delete(ids);
            jsonMap.put("success", true);
        } catch (Exception e) {
            jsonMap.put("success", false);
            e.printStackTrace();
        }
        return jsonMap;
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
