package com.isomer.device.controller;

import com.isomer.common.pojo.ApiResult;
import com.isomer.device.domain.DeviceInfo;
import com.isomer.device.service.DeviceService;
import com.isomer.pretreatment.encrypt.annotation.Decryption;
import com.isomer.pretreatment.generate.annotation.ValueGenerate;
import com.isomer.pretreatment.validate.annotation.ParamValidate;
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

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/register")
    public ApiResult<?> register(@RequestBody
                                 @ValueGenerate
                                 @ParamValidate(names = {"tag", "name"}) DeviceInfo info) {
        return deviceService.register(info);
    }

    @PostMapping("/push")
    public ApiResult<?> pushData(@RequestBody String message,
                                 @RequestParam("id") @Decryption String id,
                                 @RequestParam("tag") @Decryption String tag) {
        System.out.println(message);
        System.out.println(id);
        System.out.println(tag);

        return ApiResult.succeed();
    }
}
