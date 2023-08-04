package com.isomer.messaging.mqtt.config;

import com.isomer.messaging.mqtt.properties.MqttProperties;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 21:27
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttConfig {

    @Bean("enable")
    @ConditionalOnProperty(value = "mqtt.enable", havingValue = "true")
    public MqttClient enable(MqttProperties properties) {
        MqttClient client;
        try {
            client = new MqttClient(properties.getHost(), genClientId(), null);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Bean("data")
    @ConditionalOnProperty(value = "mqtt.enable", havingValue = "true")
    public MqttClient data(MqttProperties properties) {
        MqttClient client;
        try {
            client = new MqttClient(properties.getHost(), genClientId(), null);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    private String genClientId() {
        return "ISOMER-" + UUID.randomUUID().toString()
                .toUpperCase().replace("-", "").substring(0, 10);
    }
}
