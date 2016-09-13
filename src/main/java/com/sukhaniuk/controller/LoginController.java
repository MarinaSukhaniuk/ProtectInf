package com.sukhaniuk.controller;

import com.sukhaniuk.storage.UsersStorage;
import com.sukhaniuk.utils.GlobalController;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by shyshkin_vlad on 10.04.16.
 */
@SuppressWarnings("unused")
@Controller
public class LoginController extends GlobalController {
    private static final Logger log = Logger.getLogger(LoginController.class.getName());

    @RequestMapping(value = "login")
    public String login(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        return "login";
    }

    @RequestMapping(value = "login/signin")
    public String signin(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("passwordes");
        UsersStorage.login(email,password);
        System.out.println("--"+password+"  "+email);
        return "login";
    }

    @RequestMapping(value = "login/signout")
    public String signout(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        return "login";
    }
}
