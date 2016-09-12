package com.shyslav.controller;

import com.shyslav.utils.GlobalController;
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
public class HomeController extends GlobalController {
    private static final Logger log = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "index")
    public String home(ModelMap map, HttpServletRequest request) throws IOException, JSONException, SQLException {
        setPageTitle(map, "Home page");
        return "index.jsp";
    }
}
