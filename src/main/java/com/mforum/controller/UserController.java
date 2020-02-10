package com.mforum.controller;

import com.mforum.model.User;
import com.mforum.service.impl.UserServiceImpl;
import com.mforum.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    UserServiceImpl service;

    /**
     * 用户登录
     * localhost:8080/user/login?username=www&password=123
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("login")
    public JsonResult<User> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        User user = service.login(username,password);
        session.setAttribute("id",user.getId());
        session.setAttribute("login",user.getLogin());
        session.setAttribute("user",user);
        return new JsonResult<User>(SUCCESS,user);
    }
    @RequestMapping("logout")
    public JsonResult<Void> logout(HttpSession session){
        session.invalidate();
        return new JsonResult<>(SUCCESS);
    }
}
