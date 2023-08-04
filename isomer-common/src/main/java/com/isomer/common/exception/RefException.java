package com.isomer.common.exception;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/4 10:03
 */
public class RefException extends RuntimeException {

    public RefException(String refMethodName, Throwable cause) {
        super("Reference method [" + refMethodName + "] call failed", cause);
    }
}
