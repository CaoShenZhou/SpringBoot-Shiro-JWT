package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @version v1.0.0
 * @className: ServerResponseVO
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/17/10:37
 **/
@Data
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    // 响应码
    private Integer code;

    // 描述信息
    private String message;

    // 响应内容
    private T data;
}