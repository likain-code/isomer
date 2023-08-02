package com.isomer.device.mapper;

import com.isomer.device.domain.DeviceInfo;
import com.isomer.device.domain.DeviceStatus;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 10:15
 */
public interface DeviceMapper {

    int insertDeviceInfo(DeviceInfo info);

    int insertDeviceStatus(DeviceStatus status);
}
