package com.example.demo.filter;

import com.example.demo.realm.JwtToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @version v1.0.0
 * @className: JwtFilter
 * @author: Mr.Cao
 * @description: TODO Jwt过滤器
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
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求的请求头是否携带Token
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 Token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Token");
        return token != null;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 执行登陆
     * @version: v1.0.0
     * @date 2019/12/29/9:58
     **/
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取Token
        String token = httpServletRequest.getHeader("Token");
        JwtToken jwtToken = new JwtToken(token);
        //提交给realm进行登入验证
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

}
