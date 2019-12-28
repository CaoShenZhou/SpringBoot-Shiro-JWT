package com.example.demo.filter;

import com.example.demo.realm.JwtToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @version v1.0.0
 * @className: JwtFilter
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/28/15:23
 **/
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * @author: Mr.Cao
     * @description: TODO 检查是否携带token
     * @version: v1.0.0
     * @date 2019/12/28/15:43
     **/
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        //判断请求的请求头是否带上 "Token"
        if (((HttpServletRequest) request).getHeader("Token") != null) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }
}
