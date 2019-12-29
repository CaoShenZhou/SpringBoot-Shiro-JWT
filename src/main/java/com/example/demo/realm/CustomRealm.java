package com.example.demo.realm;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @version v1.0.0
 * @className: CustomRealm
 * @author: Mr.Cao
 * @description: TODO 自定义域
 * @date: 2019/12/17/10:28
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * @author: Mr.Cao
     * @description: TODO 获取认证信息
     * @version: v1.0.0
     * @date 2019/12/28/11:38
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证————");
        //登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        String password = userService.getPassword(token.getUsername());
        if (null == password) {
            throw new AccountException("用户不存在");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 获取授权信息
     * @version: v1.0.0
     * @date 2019/12/28/11:38
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //获取用户信息
        User user = userService.getUser(username);
        //添加角色
        simpleAuthorizationInfo.addRole(user.getRole());
        //设置权限
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }
}