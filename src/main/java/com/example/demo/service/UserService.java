package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version v1.0.0
 * @className: UserService
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/01/03/下午 05:34
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
