package com.isomer.messaging.mqtt.exception.basic;

import com.isomer.messaging.mqtt.exception.IsomerMqttException;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/5 15:53
 */
public class SubscribeException extends IsomerMqttException {

    public SubscribeException(String message, Throwable cause) {
        super(message, cause);
    }
}
