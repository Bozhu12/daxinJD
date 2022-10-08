package com.sans.exception;
import com.sans.model.enums.StateCode;
import com.sans.utils.BaseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 * @author Sans
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获指定异常响应
     * @param e 捕获异常类
     * @return 异常响应
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResult businessExceptionHandler(BusinessException e) {
        return BaseResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 运行时异常
     * @param e 捕获异常类
     * @return 异常响应
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResult runtimeExceptionHandler(RuntimeException e) {
        return BaseResult.error(StateCode.SYSTEM_ERROR , e.getMessage());
    }
}
