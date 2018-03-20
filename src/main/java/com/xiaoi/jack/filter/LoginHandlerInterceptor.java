package com.xiaoi.jack.filter;


import com.xiaoi.jack.constant.SessionConstant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 实现登录拦截
 *
 * @author jack
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //后台session控制
        String uri = request.getRequestURI();
        Object username =  request.getSession().getAttribute(SessionConstant.SESSION_IS_LOGIN);
        if(username!=null){
            return true;
        }
        request.getRequestDispatcher("WEB-INF/html/Login.html").forward(request, response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
