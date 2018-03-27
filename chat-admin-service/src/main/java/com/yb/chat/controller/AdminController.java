/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.controller;

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

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable String name, Model model) {

        List<String> list = adminService.findOnline();
        model.addAttribute("list", list);
        model.addAttribute("size", list.size());
        model.addAttribute("name", name);
        return "index";
    }
}
