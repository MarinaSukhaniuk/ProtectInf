package com.sukhaniuk.controller;

import com.sukhaniuk.utils.GlobalController;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by sukhaniuk on 10.04.16.
 */
@SuppressWarnings("unused")
@Controller
public class HomeController extends GlobalController {
    private static final Logger log = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = {"index","/",""})
    public String home(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        setPageTitle(map, "Home page");
        return "index";
    }
    @RequestMapping(value = {"SignIn","/",""})
    public String SignIn(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        setPageTitle(map, "SignIn page");
        return "SignIn";
    }
    @RequestMapping(value = "/SignIn/send")
    public String addResponses(ModelMap map, HttpServletRequest request, RedirectAttributes redirAtr)
    {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String email = request.getParameter("email");
        String password = request.getParameter("passwordes");

        System.out.println("--"+password+"  "+email);

            //DatabaseInsert.insert("reports",new String [] {name,message,dateFormat.format(date),email,phone,"-"},new String [] {"author","rText","rDate","mail","phone","vision"});
            return "redirect:/index.htm";
        }
    }


