package com.example.demo.entity;

import lombok.Data;

/**
 * @version v1.0.0
 * @className: User
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/17/10:16
 **/
@Data
public class User {
    private Integer id;

    private String username;

    private String password;

    private String role;

    private String permission;
}