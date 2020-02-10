package com.mforum.service.impl;

import com.mforum.dto.GithubUser;
import com.mforum.mapper.UserMapper;
import com.mforum.model.User;
import com.mforum.service.IUserService;
import com.mforum.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void reg(User user) throws UsernameDuplicateException, InsertException {

    }

    @Override
    public User gitHubLogin(GithubUser githubUser) {
        User user = userMapper.selectByAccountId(githubUser.getId());
        if (user==null){
            User user1 = new User();
            System.out.println(githubUser.getId());
            user1.setAccountId(githubUser.getId());
            user1.setLogin(githubUser.getLogin());
            user1.setName(githubUser.getName());
            user1.setCreateUser(githubUser.getLogin());
            user1.setAvatar(githubUser.getAvatar_url());
            user1.setCreateTime(new Date());
            userMapper.insert(user1);
            return user1;
        }
        return user;
    }

    @Override
    public User login(String login, String password) throws PassWordNotMatchException, UserNotFoundException {
        User user = userMapper.selectByLogin(login);
        System.err.println(user);
        if (user==null){
            throw new UserNotFoundException("登录失败,用户不存在");
        }
        if (user.getAccountId()!=null){
            throw new UserTypeException("该用户是GitHub账户,需用GitHub登录");
        }
        /*if (user.getIsDelete()==1){
            throw new UserNotFoundException("登录失败,用户不存在");
        }*/
        if (!user.getPassword().equals(password)){
            throw new PassWordNotMatchException("登录失败,密码错误");
        }
        user.setPassword(null);
        user.setSalt(null);
        user.setIsDelete(null);
        return user;
    }
}
