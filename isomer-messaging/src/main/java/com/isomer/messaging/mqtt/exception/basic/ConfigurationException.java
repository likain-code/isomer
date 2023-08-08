package com.isomer.messaging.mqtt.exception.basic;

import com.isomer.messaging.mqtt.exception.IsomerMqttException;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/4 16:35
 */
public class ConfigurationException extends IsomerMqttException {

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
