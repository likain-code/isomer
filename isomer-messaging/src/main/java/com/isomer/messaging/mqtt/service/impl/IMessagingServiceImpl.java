package com.isomer.messaging.mqtt.service.impl;

import com.isomer.api.messaging.service.IMessagingService;
import com.isomer.common.pojo.ApiResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 10:51
 */
@DubboService
public class IMessagingServiceImpl implements IMessagingService {

    @Autowired
    @Qualifier("enable")
    private MqttClient mqttClient;

    @Override
    public void subscribe(String topic) {
        try {
            mqttClient.subscribe(topic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
