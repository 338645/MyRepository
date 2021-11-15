package com.bjpowernode.handlers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyController4 extends MultiActionController {

    public ModelAndView doFirst(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView res = new ModelAndView();
        res.addObject("message", "执行doFirst方法！");
        res.setViewName("welcome");
        return res;
    }

    public ModelAndView doSecond(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView res = new ModelAndView();
        res.addObject("message", "执行doSecond方法！");
        res.setViewName("welcome");
        return res;
    }

    public ModelAndView doThird(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("taoBao");
    }

    public ModelAndView doForth(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("ecommerce");
    }

}
