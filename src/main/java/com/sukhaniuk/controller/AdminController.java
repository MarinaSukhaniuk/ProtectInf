package com.sukhaniuk.controller;

import com.sukhaniuk.databases.configuration.DatabaseConnection;
import com.sukhaniuk.databases.insert.DatabaseInsert;
import com.sukhaniuk.databases.models.Alert;
import com.sukhaniuk.databases.models.User;
import com.sukhaniuk.databases.select.SelectCommand;
import com.sukhaniuk.databases.update.UpdateCommand;
import com.sukhaniuk.storage.UsersStorage;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@Controller
public class AdminController {
    public static final int[] ROLE = {7};

    @RequestMapping(value = "/admin/index")
    public String home(ModelMap map, HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "admin/changePassword")
    public String changePassword(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        return "changePassword";
    }

    @RequestMapping(value = "admin/changePassword/confirm")
    public void changePasswordConfirm(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        if (oldPassword.matches(request.getSession().getAttribute("password").toString())) {
            SelectCommand.UpdatePassword(newPassword, request.getSession().getAttribute("email").toString());
            Alert alert = new Alert("success", "Changing of password succeed", "Password is changed");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/admin/index.htm");
        } else {
            Alert alert = new Alert("danger", "Changing of password failed", "Passwors don`t match");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/admin/changePassword.htm");
        }
        return;
    }

    @RequestMapping(value = "admin/listUsers")
    public String viewUsers(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        SelectCommand sel = new SelectCommand();
        List<User> userList = sel.GetUsers();
        request.setAttribute("users", userList);
        return "listUsers";
    }

    @RequestMapping(value = "admin/block")
    public void blockUser(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        UsersStorage storage = (UsersStorage) request.getSession().getAttribute("storage");
        String id = request.getParameter("id");
        if (storage.getUser().getId() == Integer.parseInt(id)) {
            Alert alert = new Alert("danger", "Error", "You can`t block yourself");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/admin/listUsers.htm");
            return;
        }
        String comm = request.getParameter("comm");
        if (comm.equals("block")) {
            UpdateCommand.updateTable("users",
                    new String[]{"role_id = '" + 1 + "'"},
                    new String[]{"id = '" + id + "'"});
        } else {
            UpdateCommand.updateTable("users",
                    new String[]{"role_id = '" + 3 + "'"},
                    new String[]{"id = '" + id + "'"});
        }
        Alert alert = new Alert("success", "Success", "User's permission changed");
        request.getSession().setAttribute("alert", alert);
        response.sendRedirect("/admin/listUsers.htm");
    }
    @RequestMapping(value = "admin/createuser")
    public void createuser(ModelMap map, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, SQLException {
        String userName = request.getParameter("username");
        if (userName == null
                || userName.length()<3
                || SelectCommand.selectUserByMail(userName)!=null){
            Alert alert = new Alert("danger", "Error", "Wrong username");
            request.getSession().setAttribute("alert", alert);
            response.sendRedirect("/admin/listUsers.htm");
            return;
        }
        DatabaseInsert.prepareInsert("users",new Object[]{userName,3,1234}, new String[]{"email","role_id","password"});
        Alert alert = new Alert("success", "Success", "User has been created");
        request.getSession().setAttribute("alert", alert);
        response.sendRedirect("/admin/listUsers.htm");
    }
}
