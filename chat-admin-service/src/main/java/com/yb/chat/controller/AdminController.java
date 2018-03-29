/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.controller;

import com.google.common.base.Strings;

import com.yb.chat.service.AdminService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * AdminController:
 *
 * @author yangbo
 * @version 1.00
 * @since 2018/3/26 0026 11:31
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello(Model model, HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "index";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("admin") != null){
            List<String> list = adminService.findOnline();
            model.addAttribute("online", list);
            model.addAttribute("size", list.size());
            return "index";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, HttpSession session) {
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        if ("admin".equals(user) && "admin".equals(password)) {
            List<String> list = adminService.findOnline();
            model.addAttribute("online", list);
            model.addAttribute("size", list.size());
            session.setAttribute("admin", "admin");
            return "index";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model, HttpSession session) {
        if (session.getAttribute("admin") != null){

            return "user";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("admin");
        return "login";
    }
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public Object allUser(HttpServletRequest request) {
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        int c;
        int p;
        c = Strings.isNullOrEmpty(currentPage) ? 1 : Integer.valueOf(currentPage);
        p = Strings.isNullOrEmpty(pageSize) ? 10 : Integer.valueOf(pageSize);
        Object users= adminService.findAllUser(c, p);
        return users;
    }

    /**
     * 查询最后登录时间
     * @param name 用户名
     *
     * @return 时间戳
     */
    @RequestMapping(value = "/userLastLoginTime/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Object userLastLoginTime(@PathVariable("name") String name) {
        return adminService.userLastLoginTime(name);
    }

    @RequestMapping(value = "/logInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object logInfo(HttpServletRequest request) {
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");
        int c;
        int p;
        c = Strings.isNullOrEmpty(currentPage) ? 1 : Integer.valueOf(currentPage);
        p = Strings.isNullOrEmpty(pageSize) ? 10 : Integer.valueOf(pageSize);
        return adminService.log(name,c,p,sort);
    }
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log (HttpSession session) {
        if (session.getAttribute("admin") != null){

            return "log";
        } else {
            return "login";
        }
    }
}
