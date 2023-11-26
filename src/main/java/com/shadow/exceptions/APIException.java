package com.shadow.exceptions;

import lombok.Getter;

/**
 * @ClassName APIException
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/26 20:46
 * @Version 1.0
 **/
@Getter
public class APIException extends RuntimeException{
    private int code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
