package com.mforum.service;

import com.mforum.dto.GithubUser;
import com.mforum.mapper.UserMapper;
import com.mforum.model.User;
import com.mforum.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserServiceImpl userService;
    @Test
    public void githubLogin(){
        GithubUser githubUser = new GithubUser();
        githubUser.setId(12L);
        githubUser.setLogin("2312");
        githubUser.setName("dfgs");
        User user = userService.gitHubLogin(githubUser);
        System.out.println(user);
    }
}
