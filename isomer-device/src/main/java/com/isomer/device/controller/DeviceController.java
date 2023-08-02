package com.isomer.device.controller;

import com.isomer.common.pojo.ApiResult;
import com.isomer.device.domain.DeviceInfo;
import com.isomer.device.service.DeviceService;
import com.isomer.pretreatment.generate.annotation.ValueGenerate;
import com.isomer.pretreatment.validate.annotation.ParamValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/register")
    public ApiResult<?> register(
            @RequestBody @ValueGenerate
            @ParamValidate(names = {"tag", "name"}) DeviceInfo info) {
        return deviceService.register(info);
    }
}
