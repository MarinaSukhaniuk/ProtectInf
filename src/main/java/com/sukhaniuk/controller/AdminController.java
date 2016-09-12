package com.sukhaniuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AdminController {
    @RequestMapping(value = "/admin/index")
    public String home(ModelMap map, HttpServletRequest request) {
        return "index";
    }
}
