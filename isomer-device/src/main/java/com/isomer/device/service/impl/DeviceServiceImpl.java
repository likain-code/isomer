package com.isomer.device.service.impl;

import com.isomer.api.messaging.service.IMessagingService;
import com.isomer.common.async.AsyncCenter;
import com.isomer.common.exception.RefException;
import com.isomer.common.logger.LoggerUtil;
import com.isomer.common.pojo.ApiResult;
import com.isomer.common.utils.MqttTopicUtil;
import com.isomer.common.utils.SecretUtil;
import com.isomer.device.domain.DeviceInfo;
import com.isomer.device.domain.DeviceStatus;
import com.isomer.device.dto.DeviceRegisterDTO;
import com.isomer.device.exception.DeviceRegisterTsException;
import com.isomer.device.mapper.DeviceMapper;
import com.isomer.device.service.DeviceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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
    @DubboReference(check = false)
    private IMessagingService iMessagingService;

    @Override
    public ApiResult<?> register(DeviceInfo info) {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus ts = transactionManager.getTransaction(td);

        String secret = SecretUtil.gen();
        info.setSecret(secret);
        DeviceStatus status = new DeviceStatus();
        status.setDeviceInfoId(info.getId());

        try {
            deviceMapper.insertDeviceInfo(info);
            deviceMapper.insertDeviceStatus(status);
        } catch (Throwable cause) {
            transactionManager.rollback(ts);
            throw new DeviceRegisterTsException("Exception occurred when device-register", cause);
        }
        transactionManager.commit(ts);

        AsyncCenter.commit(
                () -> {
                    try {
                        iMessagingService.subscribe(MqttTopicUtil.genUpLink(info.getTag(), info.getId()));
                    } catch (Exception e) {
                        throw new RefException("subscribe", e);
                    }
                }
        );

        String upLink = MqttTopicUtil.genUpLink(info.getTag(), info.getId());
        String downLink = MqttTopicUtil.genDownLink(info.getTag(), info.getId());

        LoggerUtil.INFO.print(this.getClass(), "Device " + info.getId() + " register successfully");
        return ApiResult.succeed(new DeviceRegisterDTO(upLink, downLink, secret));
    }
}
