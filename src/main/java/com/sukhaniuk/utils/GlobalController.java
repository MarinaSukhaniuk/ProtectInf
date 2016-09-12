package com.sukhaniuk.utils;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Created by sukhaniuk on 9/9/16.
 */
public class GlobalController {
    private static final Logger log = Logger.getLogger(GlobalController.class.getName());
    protected void set(HttpServletRequest request, HttpServletResponse response){

    }
    protected void setPageTitle(ModelMap map, String title) {
        map.put("title", title);
    }
}
