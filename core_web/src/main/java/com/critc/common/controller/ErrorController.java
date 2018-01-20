package com.critc.common.controller;

import com.critc.util.code.ReturnCodeUtil;
import com.critc.util.string.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * what: 操作失败controller，用于全局统一处理失败操作
 *
 * @author 孔垂云 created on 2017年11月6日
 */
@Controller
@RequestMapping("/")
public class ErrorController {

    /**
     * what: 异常处理
     *
     * @param request request
     * @param response response
     *
     * @return 到error页面
     *
     * @author 孔垂云 created on 2017年11月6日
     */
    @RequestMapping("/error")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/common/error");
        String msg = ReturnCodeUtil.getMsg(request.getParameter("resultCode"));
        mv.addObject("msg", msg);
        // mv.addObject("msg",
        // StringUtil.decodeUrl(request.getParameter("msg")));
        mv.addObject("backUrl", StringUtil.decodeUrl(request.getParameter("backUrl")));
        return mv;
    }

    /**
     * what: 拦截404
     *
     * @param request request
     * @param response response
     *
     * @return 到notFound.htm
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/toNotFound")
    public String toNotFound(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/notFound.htm";
    }

    /**
     * what: 跳转到404页面
     *
     * @param request request
     * @param response response
     *
     * @return 到404页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/notFound")
    public ModelAndView notFound(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/common/404");
        return mv;
    }

    /**
     * what: 拦截500
     *
     * @param request request
     * @param response response
     *
     * @return 到serverError.htm
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/toServerError")
    public String toServerError(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/serverError.htm";
    }

    /**
     * what: 到500页面
     *
     * @param request request
     * @param response response
     *
     * @return 到500页面
     *
     * @author 李红 created on 2017年10月30日
     */
    @RequestMapping("/serverError")
    public ModelAndView serverError(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", request.getParameter("message"));
        mv.setViewName("/common/500");
        return mv;
    }
}
