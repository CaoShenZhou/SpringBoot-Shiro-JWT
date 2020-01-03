package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * @version v1.0.0
 * @className: JwtUtil
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2020/01/03/下午 04:44
 **/
public class JwtUtil {
    //过期时间 24 小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    //密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * @author: Mr.Cao
     * @description: TODO 生成Token
     * @version: v1.0.0
     * @date 2019/12/28/15:39
     **/
    public static String createToken(String username) {
        //到期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 验证Token是否正确
     * @version: v1.0.0
     * @date 2019/12/28/15:40
     **/
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 获取用户名
     * @version: v1.0.0
     * @date 2019/12/28/15:41
     **/
    public static String getUsername(String token) {
        try {
            return JWT.decode(token).getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
