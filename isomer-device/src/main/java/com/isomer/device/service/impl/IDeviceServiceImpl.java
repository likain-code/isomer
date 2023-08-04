package com.isomer.device.service.impl;

import com.isomer.api.messaging.dto.SelectedDTO;
import com.isomer.api.messaging.service.IDeviceService;
import com.isomer.common.enums.Status;
import com.isomer.common.logger.LoggerUtil;
import com.isomer.common.pojo.ApiResult;
import com.isomer.common.utils.ObjectUtil;
import com.isomer.device.mapper.DeviceMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/3 14:26
 */
@DubboService
public class IDeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ApiResult<?> enableDevice(Long id, String secret) {
        String realSecret = deviceMapper.queryDeviceSecret(id);
        if (realSecret == null) {
            return ApiResult.failed(Status.DEVICE_UNREGISTER);
        }
        if (!ObjectUtil.equals(secret, realSecret)) {
            return ApiResult.failed(Status.SECRET_NOT_MATCH);
        }
        Integer enable = deviceMapper.queryDeviceEnable(id);
        if (ObjectUtil.equals(enable, 1)) {
            return ApiResult.failed(Status.REPEATABLE_ENABLE);
        }

        deviceMapper.enableDevice(id, new Timestamp(System.currentTimeMillis()));
        return ApiResult.succeed();
    }

    @Override
    public ApiResult<List<SelectedDTO>> fetchSelected(Boolean enable) {
        return ApiResult.succeed(deviceMapper.querySelected(enable));
    }
}
