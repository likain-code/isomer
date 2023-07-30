package com.isomer.user.controller;

import com.isomer.common.pojo.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 10:28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/login")
    public ApiResult login() {
        return ApiResult.succeed();
    }
}
