package com.isomer.device.service.impl;

import com.isomer.api.messaging.dto.DeviceStatusDTO;
import com.isomer.api.messaging.service.MessagingService;
import com.isomer.common.pojo.ApiResult;
import com.isomer.common.utils.SecretUtil;
import com.isomer.device.domain.DeviceInfo;
import com.isomer.device.domain.DeviceStatus;
import com.isomer.device.mapper.DeviceMapper;
import com.isomer.device.service.DeviceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.UUID;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 10:28
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @DubboReference
    private MessagingService messagingService;

    @Override
    public ApiResult<?> register(DeviceInfo info) {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus ts = transactionManager.getTransaction(td);

        try {
            info.setSecret(SecretUtil.gen());
            deviceMapper.insertDeviceInfo(info);

            DeviceStatus status = new DeviceStatus();
            status.setDeviceInfoId(info.getId());
            deviceMapper.insertDeviceStatus(status);

            transactionManager.commit(ts);
            return ApiResult.succeed();
        } catch (Exception e) {
            transactionManager.rollback(ts);
            throw new RuntimeException(e);
        }
    }
}
