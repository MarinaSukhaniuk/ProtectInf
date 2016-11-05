package com.sukhaniuk.controller;

import com.sukhaniuk.Validation;
import com.sukhaniuk.databases.models.Alert;
import com.sukhaniuk.databases.models.User;
import com.sukhaniuk.databases.select.SelectCommand;
import com.sukhaniuk.storage.UsersStorage;
import com.sukhaniuk.utils.GlobalController;
import com.sun.org.apache.bcel.internal.generic.Select;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public static final int[] ROLE = {0, 1};
    private User user;

    @RequestMapping(value = "login")
    public String login(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        //Alert alert = new Alert("success", "Check ok", "password is ok");
        //map.put("alert",alert);д
        return "login";
    }


    @RequestMapping(value = "login/signin")
    public void signin(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("passwordes");
        System.out.println("Password is " + password);
        //check of password. If not null
        if (UsersStorage.getTries() > 3) {
            response.sendError(808);
            return;
        }
        if (email != null || password != null) {
            //Check of password. If the length is ok
            if (Validation.checkpassword(password)) {
                //Check of user. If user exists in db
                if (Validation.checkuser(password, email)) {
                    User user = SelectCommand.selectUserByMail(email);
                    UsersStorage storage = UsersStorage.login(email, password, user.getRole());
                    Alert alert = new Alert("success", "Authorization succeed", "You are logged in");
                    request.getSession().setAttribute("alert", alert);
                    request.getSession().setAttribute("login", user.getRole().getName());
                    request.getSession().setAttribute("storage", storage);
                    request.getSession().setAttribute("password", password);
                    request.getSession().setAttribute("email", email);
                    //if user is admin
                    if (user.getRole().getPermission() == 7) {
                        response.sendRedirect("/admin/index.htm");
                    } else response.sendRedirect("/index.htm");
                    //Check of users. If users doesn`t exist in db
                } else {
                    Alert alert = new Alert("danger", "Authorization failed", "Your password or login are wrong");
                    request.getSession().setAttribute("alert", alert);
                    response.sendRedirect("/login.htm");
                    UsersStorage.setTries();
                    return;
                }
                //Check of password. If length is not ok
            } else {
                Alert alert = new Alert("danger", "Authorization failed", "Password is too short");
                request.getSession().setAttribute("alert", alert);
                response.sendRedirect("/login.htm");
                UsersStorage.setTries();
                return;
            }
            //Check of password. If length is null
        } else {
            Alert alert = new Alert("danger", "Authorization failed", "You didn`t enter password or login");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/login.htm");
            System.out.println("NULL pass");
            UsersStorage.setTries();
            return;
        }
        //response.sendRedirect("/index.htm");
    }

    @RequestMapping(value = "/logout")
    public String logout(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        System.out.println("---------logout");
        UsersStorage usersStorage = (UsersStorage) request.getSession().getAttribute("storage");
        if (usersStorage != null)
            usersStorage.logout(request);
        return "redirect:/index.htm";
    }

    @RequestMapping(value = "admin/logout")
    public String adminlogout(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        System.out.println("---------logout");
        UsersStorage usersStorage = (UsersStorage) request.getSession().getAttribute("storage");
        if (usersStorage != null)
            usersStorage.logout(request);
        return "redirect:/index.htm";
    }

}
