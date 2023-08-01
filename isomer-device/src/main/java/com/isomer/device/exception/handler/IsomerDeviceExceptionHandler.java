package com.isomer.device.exception.handler;

import com.isomer.pretreatment.PretreatmentException;
import com.isomer.common.enums.Status;
import com.isomer.common.logger.LoggerUtil;
import com.isomer.common.pojo.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 18:22
 */
@RestControllerAdvice
public class IsomerDeviceExceptionHandler {

    @ExceptionHandler(PretreatmentException.class)
    public ApiResult<?> handleParamValidateException(PretreatmentException e) {
        LoggerUtil.ERROR.print(IsomerDeviceExceptionHandler.class, e.getMessage(), e);
        return ApiResult.failed(Status.ILLEGAL_PARAM_EXCEPTION);
    }
}
