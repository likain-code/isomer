package com.isomer.device.controller;

import com.isomer.api.messaging.service.MessagingService;
import com.isomer.common.pojo.ApiResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/reg")
    public ApiResult<?> register(@RequestParam String id) {
        return messagingService.register(id);
    }

    @PostMapping("/test")
    public ApiResult<?> test(@RequestParam String topic, @RequestParam String msg) {return messagingService.test(topic, msg);}
}
