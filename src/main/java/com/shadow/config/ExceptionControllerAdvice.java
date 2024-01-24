package com.shadow.config;

import com.shadow.vo.ResultBuilder;
import com.shadow.vo.ResultVO;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @ClassName ExceptionControllerAdvice
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/26 20:52
 * @Version 1.0
 **/
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return objectError.getDefaultMessage();
    }


    @ExceptionHandler(SQLException.class)
    public ResultVO<String> SQLExceptionHandle(SQLException e) {
        return ResultBuilder.build(500, e.getMessage(), null);
    }
}
