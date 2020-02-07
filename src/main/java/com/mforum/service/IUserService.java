package com.mforum.service;

import com.mforum.dto.GithubUser;
import com.mforum.model.User;
import com.mforum.service.ex.InsertException;
import com.mforum.service.ex.PassWordNotMatchException;
import com.mforum.service.ex.UserNotFoundException;
import com.mforum.service.ex.UsernameDuplicateException;

/**
 * 处理用户信息的业务层接口
 */
public interface IUserService {

    /**
     * 用户注册
     * @param user
     * @throws UsernameDuplicateException 用户名冲突异常
     * @throws InsertException 插入数据失败异常
     */
    void reg(User user) throws UsernameDuplicateException, InsertException;

    /**
     * Github用户登录
     * @param githubUser
     * @return
     */
    User gitHubLogin(GithubUser githubUser);

    /**
     * 用户登录
     * @param login 用户名
     * @param password 密码
     * @return 返回user对象
     * @throws PassWordNotMatchException 密码错误
     * @throws UserNotFoundException 用户不存在
     */
    User login(String login,String password) throws PassWordNotMatchException, UserNotFoundException;
}
