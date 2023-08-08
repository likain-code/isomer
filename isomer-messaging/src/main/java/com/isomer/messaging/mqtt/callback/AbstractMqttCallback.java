package com.isomer.messaging.mqtt.callback;

import com.isomer.api.messaging.dto.SelectedDTO;
import com.isomer.api.messaging.service.IDeviceService;
import com.isomer.common.exception.RefException;
import com.isomer.common.logger.LoggerUtil;
import com.isomer.common.pojo.ApiResult;
import com.isomer.common.utils.MqttTopicUtil;
import com.isomer.messaging.mqtt.exception.basic.SubscribeException;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.List;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/4 15:14
 */
public abstract class AbstractMqttCallback implements MqttCallbackExtended {

    protected String parseId(String topic) {
        String id = null;
        try {
            id = topic.split("/")[3];
        } catch (Exception e) {
            LoggerUtil.ERROR.print(this.getClass(),
                    "Error occurred when parsing device id from topic " + topic, e);
            id = "UnKnown";
        }
        return id;
    }

    protected void afterReconnection(MqttClient client,
                                     IDeviceService iDeviceService, boolean enable, String serverUrl) {
        LoggerUtil.INFO.print(this.getClass(),
                client.getClientId() + " has successfully reconnected to server " + serverUrl);

        ApiResult<List<SelectedDTO>> fetched = fetchSubscribe(iDeviceService, enable);

        doResubscribe(fetched.getEntity(), client);
    }

    private ApiResult<List<SelectedDTO>> fetchSubscribe(IDeviceService iDeviceService, boolean enable) {
        ApiResult<List<SelectedDTO>> enabled;
        try {
            enabled = iDeviceService.fetchSelected(enable);
        } catch (Exception e) {
            throw new RefException("fetchSelected", e);
        }
        return enabled;
    }

    private void doResubscribe(List<SelectedDTO> dtoList, MqttClient client) {
        String upLinkTopic = null;
        for (SelectedDTO dto : dtoList) {
            try {
                upLinkTopic = MqttTopicUtil.genUpLink(dto.getTag(), dto.getId());
                client.subscribe(upLinkTopic);
            } catch (MqttException e) {
                LoggerUtil.ERROR.print(this.getClass(),
                        client.getClientId() + " failed to subscribe topic " + upLinkTopic);
                throw new SubscribeException(client.getClientId() + " failed to subscribe topic " + upLinkTopic, e);
            }
            LoggerUtil.INFO.print(this.getClass(),
                    client.getClientId() + " has subscribe topic " + upLinkTopic);
        }
    }
}
