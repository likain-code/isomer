package com.isomer.messaging.mqtt.service.impl;

import com.isomer.api.messaging.service.MessagingService;
import com.isomer.common.pojo.ApiResult;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 10:51
 */
@DubboService
public class MessagingServiceImpl implements MessagingService {

    @Override
    public ApiResult<?> register(String id) {
        return ApiResult.succeed();
    }

    @Override
    public ApiResult<?> test(String topic, String msg) {
        return ApiResult.succeed();
    }
}
