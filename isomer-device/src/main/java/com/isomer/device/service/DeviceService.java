package com.isomer.device.service;

import com.isomer.common.pojo.ApiResult;
import com.isomer.device.domain.DeviceBasic;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 10:28
 */
public interface DeviceService {

    ApiResult register(DeviceBasic basic);
}
