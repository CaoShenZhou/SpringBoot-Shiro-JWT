package com.example.demo.mapper;

import com.example.demo.entity.User;

/**
 * @version v1.0.0
 * @className: UserMapper
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/17/10:21
 **/
public interface UserMapper {

    String getPassword(String username);

    String getRole(String username);

    User getUser(String username);
}
