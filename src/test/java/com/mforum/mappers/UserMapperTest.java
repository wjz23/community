package com.mforum.mappers;

import com.mforum.mapper.UserMapper;
import com.mforum.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User("wjz","ww",123L,"addd");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void selectAll(){
        User[] users = userMapper.selectAll();
        for (User user:
             users) {
            System.out.println(user);
        }
    }
    @Test
    public void selectByAccountId(){
        User user = userMapper.selectByAccountId(123L);
        System.out.println(user);
    }
    @Test
    public void selectByLogin(){
        User user = userMapper.selectByLogin("wjz23");
        System.err.println(user);
    }
}
