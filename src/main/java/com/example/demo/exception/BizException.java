package com.example.demo.exception;

import lombok.Data;

/**
 * @version v1.0.0
 * @className: MyException
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/12/9/16:37
 **/
@Data
public class BizException extends RuntimeException {

    private int code;

    private String msg;

    public BizException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
