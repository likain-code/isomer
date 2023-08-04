package com.isomer.messaging.mqtt.callback;

import org.eclipse.paho.client.mqttv3.MqttCallback;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/4 15:14
 */
public abstract class AbstractMqttCallback implements MqttCallback {

    protected String parseTag(String topic) {
        return topic.split("/")[2];
    }

    protected String parseId(String topic) {
        return topic.split("/")[3];
    }
}
