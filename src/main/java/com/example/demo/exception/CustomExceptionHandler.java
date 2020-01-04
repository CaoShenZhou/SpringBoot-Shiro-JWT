package com.example.demo.exception;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.example.demo.entity.ResponseResult;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version v1.0.0
 * @className: ExceptionControllerAdvice
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/08/18/上午 12:27
 **/
@ControllerAdvice
@RestController
public class CustomExceptionHandler {

    /**
     * @author: Mr.Cao
     * @description: TODO 找不到资源异常
     * @version: v1.0.0
     * @date 2019/08/18/下午 05:39
     **/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseResult noHandlerFoundException() {
        ResponseResult response = new ResponseResult();
        response.content(404, "找不到资源异常");
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 请求方法异常
     * @version: v1.0.0
     * @date 2019/08/18/下午 05:16
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult httpRequestMethodNotSupportedException() {
        ResponseResult response = new ResponseResult();
        response.content(405, "请求方法异常");
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 媒体类型异常
     * @version: v1.0.0
     * @date 2019/08/18/下午 05:36
     **/
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseResult httpMediaTypeNotSupportedException() {
        ResponseResult response = new ResponseResult();
        response.content(415, "媒体类型异常");
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 方法参数无效异常
     * @version: v1.0.0
     * @date 2019/08/18/下午 05:17
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseResult response = new ResponseResult();
        String errorMesssage = "方法参数无效异常:" + e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        response.content(400, errorMesssage);
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 违反约束异常
     * @version: v1.0.0
     * @date 2019/11/5/16:29
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult constraintViolationException(ConstraintViolationException e) {
        ResponseResult response = new ResponseResult();
        String errorMesssage = "违反约束异常:" + e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        response.content(400, errorMesssage);
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 请求参数异常
     * @version: v1.0.0
     * @date 2019/9/21/17:42
     **/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult httpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResponseResult response = new ResponseResult();
        response.content(400, "请求参数异常:" + e.getMessage());
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 错误的SQL语法异常
     * @version: v1.0.0
     * @date 2019/11/22/11:30
     **/
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseResult badSqlGrammarException(BadSqlGrammarException e) {
        ResponseResult response = new ResponseResult();
        response.content(500, "错误的SQL语法异常:" + e.getSQLException());
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 绑定异常
     * @version: v1.0.0
     * @date 2020/1/4/19:09
     **/
    @ExceptionHandler(BindException.class)
    public ResponseResult bindException(BindException e) {
        ResponseResult response = new ResponseResult();
        String errorMesssage = "绑定异常:" + e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        response.content(400, errorMesssage);
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 认证异常
     * @version: v1.0.0
     * @date 2020/1/4/19:10
     **/
    @ExceptionHandler(AuthenticationException.class)
    public ResponseResult authenticationException(AuthenticationException e) {
        ResponseResult response = new ResponseResult();
        response.content(400, e.getMessage());
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 业务异常
     * @version: v1.0.0
     * @date 2020/1/4/19:09
     **/
    @ExceptionHandler(BizException.class)
    public ResponseResult bizException(BizException e) {
        ResponseResult response = new ResponseResult();
        response.content(e.getCode(), e.getMsg());
        return response;
    }

    /**
     * @author: Mr.Cao
     * @description: TODO 默认异常
     * @version: v1.0.0
     * @date 2019/08/18/下午 05:17
     **/
    /*
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult defaultException(Exception e) {
        ResponseResult response = new ResponseResult();
        response.setMsg("未知错误:" + e.getMessage());
        return response;
    }
     */

}

