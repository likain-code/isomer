package com.isomer.user.exception.handler;

import com.isomer.common.enums.Status;
import com.isomer.common.logger.LoggerUtil;
import com.isomer.user.exception.IllegalOriginException;
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
    public ApiResult<?> handleIllegalOriginException(IllegalOriginException e) {
        LoggerUtil.ERROR.print(IsomerUserExceptionHandler.class, e.getMessage(), e);
        return ApiResult.failed(Status.ILLEGAL_ORIGIN_EXCEPTION);
    }
}
