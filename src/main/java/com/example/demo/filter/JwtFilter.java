package com.example.demo.filter;

import com.example.demo.realm.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @version v1.0.0
 * @className: JwtFilter
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/01/03/下午 08:12
 **/
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * @author: Mr.Cao
     * @description: TODO 是否允许访问
     * @version: v1.0.0
     * @date 2020/01/03/下午 08:19
     **/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            throw new AuthenticationException("token有误");
        }
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 执行登录
     * @version: v1.0.0
     * @date 2020/01/03/下午 08:18
     **/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取token
        String token = httpServletRequest.getHeader("token");
        JwtToken jwtToken = new JwtToken(token);
        //提交给realm进行登入
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
