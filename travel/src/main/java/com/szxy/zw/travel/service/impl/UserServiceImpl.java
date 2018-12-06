package com.szxy.zw.travel.service.impl;

import com.szxy.zw.travel.exception.UserExistsException;
import com.szxy.zw.travel.mapper.UserMapper;
import com.szxy.zw.travel.pojo.User;
import com.szxy.zw.travel.service.IUserService;
import com.szxy.zw.travel.utils.Md5Util;
import com.szxy.zw.travel.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * @date 2018/12/4 9:23
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) throws Exception {
        //判断用户是否存在
        User user1 = userMapper.queryUserByUserName(user.getUsername());
        if (user1 != null) {
            throw new UserExistsException("该用户已经存在!");
        }
        //设置激活状态未未激活
        user.setStatus("N");
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //密码加密
        user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        //注册用户
        userMapper.addUser(user);
    }
}
