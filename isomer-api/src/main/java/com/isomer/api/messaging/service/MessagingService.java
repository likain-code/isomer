package com.isomer.api.messaging.service;

import com.isomer.api.messaging.dto.DeviceStatusDTO;
import com.isomer.common.pojo.ApiResult;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 10:51
 */
public interface MessagingService {

    ApiResult<?> register(DeviceStatusDTO dto);
}
