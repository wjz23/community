package com.mforum.service;

import com.mforum.dto.GithubUser;
import com.mforum.model.User;
import com.mforum.service.ex.InsertException;
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
}
