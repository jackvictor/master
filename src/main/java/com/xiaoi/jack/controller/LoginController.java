package com.xiaoi.jack.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaoi.jack.constant.SessionConstant;
import com.xiaoi.jack.service.LoginService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jack
 *
 */
@Controller
public class LoginController {
    private static Logger logger = LogManager.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @ResponseBody
    @RequestMapping("/login")
    public ModelAndView login(HttpSession session, HttpServletResponse response) throws Exception {
        //响应头设置
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        if (session.getAttribute(SessionConstant.SESSION_IS_LOGIN) != null) {
            response.sendRedirect("main");
            return new ModelAndView("Main");
        } else {
            return new ModelAndView("Login");
        }
    }

    @ResponseBody
    @RequestMapping("/login.api")
    public String checkLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        logger.info("===========login========");
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        JSONObject json = new JSONObject();
        //需要验证，避免被绕过前端直接请求接口
        if (userName.length() <= 1 || password.length() <= 5) {
            json.put("type", "failure");
            json.put("reason", "登录失败：无效的用户名或密码");
            return json.toJSONString();
        }
        if (loginService.login(userName, password, session)) {
            //登录成功
            json.put("type", "success");
            json.put("url", basePath + "/main");
        } else {
            json.put("type", "failure");
            json.put("reason", "登录失败：错误的用户名或密码");
        }
        System.out.println(json.toJSONString());
        return json.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/main/add.api")
    public String addUser(String userName,String password,String email) throws Exception {
        logger.info("======addUser======");
        JSONObject json = new JSONObject();
        if (userName.length() <= 1 || password.length() <= 5) {
            json.put("type", "failure");
            json.put("reason", "添加失败：无效的用户名或密码");
            return json.toJSONString();
        }
//        if(loginService.addNewUser(userName,password,email)){
//            json.put("type","success");
//        }
        else{
            json.put("type","failure");
        }
        return json.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/main/delete.api")
    public String deleteUser(String userId) throws Exception{
        logger.info("===deleteUser===");
        JSONObject json = new JSONObject();
        if(loginService.deleteUser(userId)){
            json.put("type","succerss");
        }else {
            json.put("type","failure");
        }
        return json.toString();
    }

}
