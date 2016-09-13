package com.sukhaniuk.controller;

import com.sukhaniuk.Validation;
import com.sukhaniuk.databases.models.Alert;
import com.sukhaniuk.storage.UsersStorage;
import com.sukhaniuk.utils.GlobalController;
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
    public static final int [] ROLE = {0,1};
    @RequestMapping(value = "login")
    public String login(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        Alert alert = new Alert("success", "Check ok", "password is ok");
        map.put("alert",alert);
        return "login";
    }

    @RequestMapping(value = "login/signin")
    public void signin(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("passwordes");
        if(Validation.checkpassword(password))
        {
            Alert alert = new Alert("success", "Check ok", "password is ok");
            map.put("alert",alert);
        }
        request.getSession().setAttribute("storage",UsersStorage.login(email,password));
        response.sendRedirect("/index.htm");
    }

    @RequestMapping(value = "login/signout")
    public String signout(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        return "login";
    }
}
