package com.mforum.controller;

import com.mforum.service.ex.*;
import com.mforum.util.JsonResult;
import org.apache.ibatis.annotations.Insert;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器的基类
 */
public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final Integer SUCCESS = 200;
    /**
     * 从session中获取用户id
     */
    protected final Integer getIdFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("user").toString());
    }
    /**
     * 从session中获取用户名
     */
    protected final String getLoginFromSession(HttpSession session){
        return session.getAttribute("login").toString();
    }

    /**
     * 异常处理
     */
    @ExceptionHandler({ServiceException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jsonResult =new JsonResult<>();
        jsonResult.setMessage(e.getMessage());
        if (e instanceof InsertException){
            jsonResult.setState(5001);
        }
        if (e instanceof PassWordNotMatchException){
            jsonResult.setState(4001);
        }
        if (e instanceof UsernameDuplicateException){
            jsonResult.setState(4002);
        }
        if (e instanceof UserNotFoundException){
            jsonResult.setState(4003);
        }
        if (e instanceof UserTypeException){
            jsonResult.setState(4004);
        }
        return jsonResult;
    }
}
