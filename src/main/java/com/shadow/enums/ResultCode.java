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
    SUCCESS(1000, "操作成功"),
    FAILED(1001, "响应失败"),
    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
