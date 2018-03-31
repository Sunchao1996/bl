package com.bl.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bl.util.code.GlobalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.Reader;
import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.ReaderCond;
import com.bl.book.dao.BookCardDao;
import com.bl.book.dao.UserDao;
import com.bl.book.service.ReaderService;
import com.bl.book.util.StatusUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chao
 * @功能描述 读者处理器类
 */
@Controller
@RequestMapping("/book/reader")
public class ReadUserController {

    @Autowired
    private ReaderService service;
    @Autowired
    private BookCardDao bookCardDao;

    @RequestMapping("/insert")
    public String insert(Reader reader, HttpServletRequest request) {
        reader.setReaderId((String) request.getAttribute("readerId"));
        reader.setReaderName((String) request.getAttribute("readerName"));
        reader.setReaderGender((String) request.getAttribute("readerGender"));
        reader.setReaderTel((String) request.getAttribute("readerTel"));
        reader.setReaderCardId((String) request.getAttribute("readerCardId"));
        reader.setReaderStatusId((Integer) request.getAttribute("readerStatusId"));
        reader.setBookCardId((String) request.getAttribute("bookCardId"));
        List<Reader> readerList = service.getByS(reader);
        if (readerList.size() > 0) {
            BookCard card = new BookCard();
            card.setBookCardId(reader.getBookCardId());
            bookCardDao.deleteT(card);
            return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;
        } else {
            int count = service.insert(reader);
            if (count > 0) {
                return "forward:/success.htm?resultCode=" + GlobalCode.OPERA_SUCCESS;
            } else {
                return "forward:/error.htm?resultCode=" + GlobalCode.OPERA_FAILURE;

            }
        }
    }


}
