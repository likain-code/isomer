package com.isomer.messaging.mqtt.init;

import com.isomer.messaging.mqtt.properties.MqttProperties;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 21:48
 */
@Component
public class MqttConnectionInit implements ApplicationRunner {

    @Autowired
    private MqttClient enable;
    @Autowired
    private MqttClient data;
    @Autowired
    private MqttProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MqttConnectOptions options = new MqttConnectOptions();
        initConnectionOptions(options);
        doInit(options, enable, data);

        enable.subscribe("isomer/enable");
        System.out.println(enable.getClientId() + " has subscribed");
        data.subscribe("isomer/data");
        System.out.println(data.getClientId() + " has subscribed");
    }

    private void initConnectionOptions(MqttConnectOptions options) {
        options.setUserName(properties.getUsername());
        options.setPassword(properties.getPassword().toCharArray());
        options.setConnectionTimeout(properties.getTimeout());
        options.setKeepAliveInterval(properties.getKeepAlive());
    }

    private void doInit(MqttConnectOptions options, MqttClient... clients) throws MqttException {
        for (MqttClient client : clients) {
            client.connect(options);
        }
    }
}
