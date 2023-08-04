package com.isomer.common.exception;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 10:26
 */
public class TsException extends RuntimeException {

    public TsException(String message) {
        super(message);
    }

    public TsException(String message, Throwable cause) {
        super(message, cause);
    }
}
