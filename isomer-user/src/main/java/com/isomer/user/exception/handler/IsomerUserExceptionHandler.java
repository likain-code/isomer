package com.isomer.user.exception.handler;

import com.isomer.common.exception.IllegalOriginException;
import com.isomer.common.pojo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 14:40
 */
@Slf4j
@RestControllerAdvice
public class IsomerUserExceptionHandler {

    @ExceptionHandler(IllegalOriginException.class)
    public ApiResult handleIllegalOriginException(IllegalOriginException e) {
        log.error(e.getMessage(), e);
        return ApiResult.failed(e.getStatus());
    }
}
