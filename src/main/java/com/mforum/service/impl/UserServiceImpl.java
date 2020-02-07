package com.mforum.service.impl;

import com.mforum.dto.GithubUser;
import com.mforum.mapper.UserMapper;
import com.mforum.model.User;
import com.mforum.service.IUserService;
import com.mforum.service.ex.InsertException;
import com.mforum.service.ex.UsernameDuplicateException;
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
            user1.setCreateTime(new Date());
            userMapper.insert(user1);
            return user1;
        }
        return user;
    }
}
