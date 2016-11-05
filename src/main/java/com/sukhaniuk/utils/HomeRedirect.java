package com.sukhaniuk.utils;

import com.sukhaniuk.controller.HomeController;
import com.sukhaniuk.controller.LoginController;
import com.sukhaniuk.license.LicensesArrayList;
import com.sukhaniuk.storage.UsersStorage;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeRedirect extends HandlerInterceptorAdapter {
    private LicensesArrayList licence = new LicensesArrayList();
    /**
     * This function Execute before spring method handler
     *
     * @param request  - servlet request
     * @param response - servlet response
     * @param handler  - handler
     * @return result enter to method or not
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!licence.checkLicense()) {
            System.out.println("LICENSE ERROR");
            System.exit(0);
        }
        if (!UsersStorage.checkRole(HomeController.ROLE)) {
            response.sendError(999);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * This function Execute after spring method handler
     *
     * @param request  - servlet request
     * @param response - servlet response
     * @param handler  - handler
     * @return result enter to method or not
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * This function Execute after spring method handled
     *
     * @param request  - servlet request
     * @param response - servlet response
     * @param handler  - handler
     * @return result enter to method or not
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}

