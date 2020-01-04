package com.example.demo.entity;

import com.example.demo.util.JsonUtil;
import lombok.Data;

import java.util.Map;

/**
 * @version v1.0.0
 * @className: Result
 * @author: Mr.Cao
 * @description: TODO
 * @date: 2019/9/16/10:49
 **/
@Data
public class ResponseResult<T> {
    private int code;

    private String msg;

    private T data;

    public void success(T data) {
        this.code = 200;
        this.msg = "操作成功";
        this.data = data;
    }

    public void content(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void content(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}

