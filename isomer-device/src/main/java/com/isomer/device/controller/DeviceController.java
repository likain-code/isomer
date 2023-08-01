package com.isomer.device.controller;

import com.isomer.pretreatment.generate.annotation.ValueGenerate;
import com.isomer.pretreatment.validate.annotation.ParamValidate;
import com.isomer.api.messaging.service.MessagingService;
import com.isomer.common.pojo.ApiResult;
import com.isomer.device.domain.DeviceBasic;
import com.isomer.device.service.DeviceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/31 10:47
 */
@RestController
@RequestMapping("/api/dv")
public class DeviceController {

    @DubboReference
    private MessagingService messagingService;
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/register")
    public ApiResult<?> register(
            @RequestBody @ValueGenerate
            @ParamValidate(types = {String.class, Long.class, Integer.class}) DeviceBasic basic) {
        return deviceService.register(basic);
    }
}
