package com.isomer.messaging.mqtt.callback;

import com.isomer.api.messaging.service.IDeviceService;
import com.isomer.common.logger.LoggerUtil;
import com.isomer.common.pojo.ApiResult;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 21:44
 */
public class EnableCallback extends AbstractMqttCallback {

    private final MqttClient enable;
    private final MqttClient data;
    private final IDeviceService iDeviceService;

    public EnableCallback(MqttClient enable, MqttClient data, IDeviceService iDeviceService) {
        this.enable = enable;
        this.data = data;
        this.iDeviceService = iDeviceService;
    }

    @Override
    public void connectionLost(Throwable throwable) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        Long id = Long.parseLong(parseId(topic));
        String secretReceived = new String(mqttMessage.getPayload());
        ApiResult<?> result = iDeviceService.enableDevice(id, secretReceived);

        if (result.getCode() != 1000) {
            LoggerUtil.ERROR.print(EnableCallback.class,
                    "Device " + id + " enable failed, cause: " + result.getDetail());
        } else {
            LoggerUtil.INFO.print(EnableCallback.class, "Device " + id + " enable successfully");
            enable.unsubscribe(topic);
            data.subscribe(topic);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }
}
