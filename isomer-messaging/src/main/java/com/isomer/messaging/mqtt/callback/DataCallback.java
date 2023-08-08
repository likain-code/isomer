package com.isomer.messaging.mqtt.callback;

import com.isomer.api.messaging.service.IDeviceService;
import com.isomer.common.logger.LoggerUtil;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 21:44
 */
public class DataCallback extends AbstractMqttCallback {

    private final MqttClient data;
    private final IDeviceService iDeviceService;

    public DataCallback(MqttClient data, IDeviceService iDeviceService) {
        this.data = data;
        this.iDeviceService = iDeviceService;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        LoggerUtil.ERROR.print(this.getClass(),
                data.getClientId() + " has lost connection to mqtt server, trying to reconnect");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        System.out.println("Device " + parseId(topic) + " " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    @Override
    public void connectComplete(boolean isReconnect, String serverUrl) {
        if (!isReconnect) {
            LoggerUtil.INFO.print(this.getClass(),
                    data.getClientId() + " has successfully connected to server " + serverUrl);
        } else {
            afterReconnection(data, iDeviceService, true, serverUrl);
        }
    }
}
