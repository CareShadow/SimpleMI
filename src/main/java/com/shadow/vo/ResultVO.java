package com.shadow.vo;

import com.shadow.enums.ResultCode;
import lombok.Getter;

/**
 * @ClassName ResultVO
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/26 17:41
 * @Version 1.0
 **/
@Getter
public class ResultVO<T> {
    private int code;
    private String msg;
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg =  resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg =  msg;
        this.data = data;
    }
}
