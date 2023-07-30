package com.isomer.common.exception;

import com.isomer.common.enums.Status;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 14:26
 */
public class IllegalOriginException extends RuntimeException {

    private final Status status;

    public IllegalOriginException(Status status) {
        super(status.getDetail());
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
