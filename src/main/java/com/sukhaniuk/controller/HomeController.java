package com.sukhaniuk.controller;

import com.sukhaniuk.databases.models.Alert;
import com.sukhaniuk.databases.select.SelectCommand;
import com.sukhaniuk.storage.UsersStorage;
import com.sukhaniuk.utils.GlobalController;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public static final int [] ROLE = {1,6,7};

    @RequestMapping(value = "changePassword")
    public String changePassword(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        return "changePassword";
    }

    @RequestMapping(value = "changePassword/confirm")
    public void changePasswordConfirm(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confNewPassword = request.getParameter("conf_new_password");
        UsersStorage storage = (UsersStorage) request.getSession().getAttribute("storage");
        if (oldPassword.equals(storage.getUser().getPassword())
                && confNewPassword.equals(newPassword)
                && newPassword.toLowerCase().matches("^[а-я]+")) {
            SelectCommand.UpdatePassword(newPassword, request.getSession().getAttribute("email").toString());
            storage.getUser().setPassword(newPassword);
            Alert alert = new Alert("success", "Changing of password succeed", "Password is changed");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/index.htm");
        } else {
            Alert alert = new Alert("danger", "Changing of password failed", "Passwors don`t match");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/changePassword.htm");
        }
        return;
    }

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


