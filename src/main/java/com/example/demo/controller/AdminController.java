package com.example.demo.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0
 * @className: AdminController
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/17/18:36
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping(value = "/getMessage")
    public String getMessage() {
        return "您拥有管理员权限，可以获得该接口的信息！";
    }

    @GetMapping(value = "/getPermission")
    //@RequiresRoles({"user","admin"})
    @RequiresRoles("admin")
    //Logical可为OR或AND
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public String getPermission() {
        return "您拥有管理员权限的view, edit权限";
    }
}
