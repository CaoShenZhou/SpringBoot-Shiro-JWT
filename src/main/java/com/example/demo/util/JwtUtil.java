package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @version v1.0.0
 * @className: jwtUtil
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/28/15:36
 **/
public class JwtUtil {
    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * @author: Mr.Cao
     * @description: TODO 生成token
     * @version: v1.0.0
     * @date 2019/12/28/15:39
     **/
    public static String createToken(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                //到期时间
                .withExpiresAt(date)
                //创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 验证token是否正确
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
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 获得token中的信息,无需secret解密
     * @version: v1.0.0
     * @date 2019/12/28/15:41
     **/
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
