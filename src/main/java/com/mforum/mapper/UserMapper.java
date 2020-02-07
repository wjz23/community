package com.mforum.mapper;

import com.mforum.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息处理
 */
public interface UserMapper {
    /**
     * 用户信息插入
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     * 查询所有用户信息
     * @return
     */
    User[] selectAll();

    /**
     * 根据githubId查询用户信息
     * @return
     */
    User selectByAccountId(Long accountId);
}
