package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @version v1.0.0
 * @className: LoginController
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/17/10:31
 **/
@RestController
@RequestMapping("/guest")
public class GuestController {
    @GetMapping(value = "/login")
    public String login() {
        return "游客";
    }

    @GetMapping(value = "/getMessage")
    public String submitLogin() {
            return "可以获取信息";
    }

}