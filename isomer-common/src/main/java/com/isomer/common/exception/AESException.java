package com.isomer.common.exception;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/8 17:16
 */
public class AESException extends Exception {

    public AESException(String message) {
        super(message);
    }

    public AESException(String message, Throwable cause) {
        super(message, cause);
    }
}
