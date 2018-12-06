package com.szxy.zw.travel.mapper;

import com.szxy.zw.travel.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangwei
 * @date 2018/12/4 9:24
 */
public interface UserMapper {
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User queryUserByUserName(@Param("username") String username);

    /**
     * 添加用户
     * @param user 用户信息
     */
    void addUser(User user);
}
