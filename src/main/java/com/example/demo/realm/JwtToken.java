package com.example.demo.realm;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @version v1.0.0
 * @className: JwtToken
 * @author: Mr.Cao
 * @description: TODO JWT令牌
 * @date: 2020/01/03/下午 04:56
 **/
@Data
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}