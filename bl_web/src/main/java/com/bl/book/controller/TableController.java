package com.bl.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sunc on 2018/1/21.
 */
@Controller
@RequestMapping("/table")
public class TableController {
    @RequestMapping("/index")
    public String index(){
        return "book/table/table";
    }
}
