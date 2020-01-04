package com.example.demo.realm;

import com.example.demo.entity.User;

import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
 * @className: ShiroRealm
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/01/03/下午 03:11
 **/
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * @author: Mr.Cao
     * @description: TODO 必须重写此方法,不然会报错
     * @version: v1.0.0
     * @date 2020/01/03/下午 03:11
     **/
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 获取授权信息
     * @version: v1.0.0
     * @date 2020/01/03/下午 11:08
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取用户名
        String username = JwtUtil.getUsername(principals.toString());
        //获取用户信息
        User user = userService.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        simpleAuthorizationInfo.addRole(user.getRole());
        //获取权限集合
        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 获取认证信息
     * @version: v1.0.0
     * @date 2020/01/04/上午 12:39
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token
        String token = (String) authenticationToken.getCredentials();
        //获取用户名
        String username = JwtUtil.getUsername(token);
        if (username == null || !JwtUtil.verify(token, username)) {
            throw new AuthenticationException("Token认证失败");
        }
        //获取用户信息
        User user = userService.getUser(username);
        if (user == null) {
            throw new AuthenticationException("该用户不存在");
        }
        if (user.getBan() == 1) {
            throw new AuthenticationException("该用户已封号");
        }
        return new SimpleAuthenticationInfo(token, token, "ShiroRealm");
    }
}
