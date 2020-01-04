package com.example.demo.controller;

import com.example.demo.entity.ResponseResult;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @version v1.0.0
 * @className: SystemCtrl
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/1/4/11:48
 **/
@CrossOrigin
@RestController
@RequestMapping("/system/")
public class SystemCtrl {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseResult login(String username, String password) {
        ResponseResult responseResult = new ResponseResult();
        User user = userService.getUser(username);
        if (user.getPassword().equals(password)) {
            String token = JwtUtil.createToken(username);
            responseResult.success(token);
            return responseResult;
        } else {
            return responseResult;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @GetMapping("notLogin")
    public String notLogin() {
        return "notLogin";
    }

    @RequiresRoles("admin")
//    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions({"view,delete"})
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
