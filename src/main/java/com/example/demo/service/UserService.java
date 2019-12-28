package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @className: UserServiceImpl
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/17/10:23
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    public String getRole(String username) {
        return userMapper.getRole(username);
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}