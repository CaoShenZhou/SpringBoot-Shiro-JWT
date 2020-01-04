package com.example.demo.mapper;

import com.example.demo.entity.User;

/**
 * @version v1.0.0
 * @className: UserMapper
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/01/03/下午 05:32
 **/
public interface UserMapper {
    User getUser(String username);
}
