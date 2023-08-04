package com.isomer.device.mapper;

import com.isomer.api.messaging.dto.SelectedDTO;
import com.isomer.device.domain.DeviceInfo;
import com.isomer.device.domain.DeviceStatus;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/1 10:15
 */
public interface DeviceMapper {

    int insertDeviceInfo(DeviceInfo info);

    int insertDeviceStatus(DeviceStatus status);

    String queryDeviceSecret(Long id);

    Integer queryDeviceEnable(Long id);

    int queryIdCount(Long id);

    List<SelectedDTO> querySelected(Boolean enable);

    int enableDevice(Long id, Timestamp timestamp);
}
