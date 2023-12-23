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

    /**
     * 成功结果构造
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> ok(T data) {
        return new ResultVO(ResultCode.SUCCESS, data);
    }

    /**
     * 自定义结果构造
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> build(int code, String msg, T data) {
        return new ResultVO(code, msg, data);
    }

    /**
     * 消息结果构造
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> create(ResultCode code, String msg) {
        return new ResultVO(code.getCode(), msg, null);
    }
}
