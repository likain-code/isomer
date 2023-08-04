package com.isomer.messaging.mqtt.init;

import com.isomer.api.messaging.dto.SelectedDTO;
import com.isomer.api.messaging.service.IDeviceService;
import com.isomer.common.exception.RefException;
import com.isomer.common.pojo.ApiResult;
import com.isomer.common.utils.MqttTopicUtil;
import com.isomer.messaging.mqtt.callback.DataCallback;
import com.isomer.messaging.mqtt.callback.EnableCallback;
import com.isomer.messaging.mqtt.properties.MqttProperties;
import org.apache.dubbo.config.annotation.DubboReference;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 21:48
 */
@Component
public class MqttInit implements ApplicationRunner {

    @Autowired
    private MqttClient enable;
    @Autowired
    private MqttClient data;
    @Autowired
    private MqttProperties properties;

    @DubboReference(check = false)
    private IDeviceService iDeviceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MqttConnectOptions options = new MqttConnectOptions();
        initConnectionOptions(options);
        doSetCallBackInit();
        doConnectionInit(options);
        doSubscribeInit();
    }

    private void initConnectionOptions(MqttConnectOptions options) {
        options.setUserName(properties.getUsername());
        options.setPassword(properties.getPassword().toCharArray());
        options.setConnectionTimeout(properties.getTimeout());
        options.setKeepAliveInterval(properties.getKeepAlive());
    }

    private void doSetCallBackInit() {
        enable.setCallback(new EnableCallback(enable, data, iDeviceService));
        data.setCallback(new DataCallback());
    }

    private void doConnectionInit(MqttConnectOptions options) throws MqttException {
        enable.connect(options);
        data.connect(options);
    }

    private void doSubscribeInit() throws MqttException {
        ApiResult<List<SelectedDTO>> registered;
        ApiResult<List<SelectedDTO>> unregistered;

        try {
            registered = iDeviceService.fetchSelected(true);
            unregistered = iDeviceService.fetchSelected(false);
        } catch (Exception e) {
            throw new RefException("fetchSelected", e);
        }

        for (SelectedDTO dto : registered.getEntity()) {
            data.subscribe(MqttTopicUtil.genUpLink(dto.getTag(), dto.getId()));
        }

        for (SelectedDTO dto : unregistered.getEntity()) {
            enable.subscribe(MqttTopicUtil.genUpLink(dto.getTag(), dto.getId()));
        }
    }
}
