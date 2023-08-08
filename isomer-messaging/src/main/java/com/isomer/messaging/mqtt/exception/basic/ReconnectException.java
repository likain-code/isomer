package com.isomer.messaging.mqtt.exception.basic;

import com.isomer.messaging.mqtt.exception.IsomerMqttException;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/5 15:44
 */
public class ReconnectException extends IsomerMqttException {

    public ReconnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
