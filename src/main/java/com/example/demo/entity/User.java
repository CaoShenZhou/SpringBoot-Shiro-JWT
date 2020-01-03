package com.example.demo.entity;

import lombok.Data;

/**
 * @version v1.0.0
 * @className: User
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/01/03/下午 05:25
 **/
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String role;

    private String permission;

    private Integer ban;
}
