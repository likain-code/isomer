package com.isomer.device.exception;

import com.isomer.common.exception.TsException;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 12:41
 */
public class DeviceRegisterTsException extends TsException {

    public DeviceRegisterTsException(String message) {
        super(message);
    }

    public DeviceRegisterTsException(String message, Throwable cause) {
        super(message, cause);
    }
}
