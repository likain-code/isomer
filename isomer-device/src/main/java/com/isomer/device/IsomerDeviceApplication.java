package com.isomer.device;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/30 15:02
 */
@SpringBootApplication
@EnableDubbo
@MapperScan("com.isomer.device.mapper")
public class IsomerDeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsomerDeviceApplication.class, args);
    }
}
