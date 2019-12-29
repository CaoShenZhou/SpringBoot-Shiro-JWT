package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0
 * @className: LoginController
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/18/8:39
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/notLogin")
    public String notLogin() {
        return "您尚未登陆！";
    }

    @GetMapping(value = "/notRole")
    public String notRole() {
        return "您没有权限！";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        //注销
        SecurityUtils.getSubject().logout();
        return "成功注销！";
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping(value = "/login")
    public String login(String username, String password) {
        String realPassword = userService.getPassword(username);
        if (realPassword == null) {
            return "用户名错误";
        } else if (!realPassword.equals(password)) {
            return "密码错误";
        } else {
            return JwtUtil.createToken(username);
        }
    }
}
