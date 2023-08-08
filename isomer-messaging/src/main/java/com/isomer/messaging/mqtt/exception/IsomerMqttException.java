package com.isomer.messaging.mqtt.exception;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/5 15:54
 */
public class IsomerMqttException extends RuntimeException {

    protected IsomerMqttException(String message, Throwable cause) {
        super(message, cause);
    }
}
