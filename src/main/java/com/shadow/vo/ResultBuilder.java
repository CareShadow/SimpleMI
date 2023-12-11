package com.shadow.vo;

import com.shadow.enums.ResultCode;

/**
 * @ClassName ResultBuilder
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/12/11 23:09
 * @Version 1.0
 **/
public class ResultBuilder {
    public static <T> ResultVO ok(T data) {
        return new ResultVO(ResultCode.SUCCESS, data);
    }

    public static <T> ResultVO build(int code, String msg, T data) {
        return new ResultVO(code, msg, data);
    }

    public static <T> ResultVO create(ResultCode code, String msg) {
        return new ResultVO(code.getCode(), msg, null);
    }
}
