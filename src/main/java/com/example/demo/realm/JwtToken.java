package com.example.demo.realm;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @version v1.0.0
 * @className: JwtToken
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/28/15:45
 **/
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
