package com.cec.settings.web.interceptor;

import com.cec.commons.constants.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class StudentLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(Constants.STUDENT) != null) {
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/pages/login.html");
        return false;
    }

}
