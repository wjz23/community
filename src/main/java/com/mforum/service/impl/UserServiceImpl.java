package com.mforum.service.impl;

import com.mforum.dto.GithubUser;
import com.mforum.mapper.UserMapper;
import com.mforum.model.User;
import com.mforum.service.IUserService;
import com.mforum.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void reg(User user) throws UsernameDuplicateException, InsertException {
        User result = userMapper.selectByLogin(user.getLogin());
        if (result!=null){
            throw new UsernameDuplicateException("注册失败,用户名已被占用");
        }
        System.err.println("reg() > password=" + user.getPassword());
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(),salt);
        user.setSalt(salt);
        user.setPassword(md5Password);
        System.err.println("reg() > salt=" + salt);
        System.err.println("reg() > md5Password=" + md5Password);

        user.setIsDelete(0);
        Date date = new Date();
        user.setCreateTime(date);
        user.setCreateUser(user.getLogin());
        user.setModifiedTime(date);
        user.setModifiedUser(user.getLogin());
        Integer rows = userMapper.insert(user);
        if (rows!=1){
            throw new InsertException("注册失败！写入数据时出现未知错误！请联系系统管理员！");
        }
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
        String salt = user.getSalt();
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
        if (!user.getPassword().equals(getMd5Password(password,salt))){
            throw new PassWordNotMatchException("登录失败,密码错误");
        }
        user.setPassword(null);
        user.setSalt(null);
        user.setIsDelete(null);
        return user;
    }

    /**
     * 对密码进行加密
     * @param password  原始密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMd5Password(String password,String salt){
        String str = password + salt;
        for (int i = 0;i < 3;i++){
            str= DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }
}
