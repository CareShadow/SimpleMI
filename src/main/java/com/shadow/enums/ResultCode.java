package com.shadow.enums;

import lombok.Getter;

/**
 * @EnumName ResultCode
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/26 17:48
 * @Version 1.0
 **/
@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(201, "响应失败"),
    UNAUTHORIZED(401, "未授权"),
    ERROR(500, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
