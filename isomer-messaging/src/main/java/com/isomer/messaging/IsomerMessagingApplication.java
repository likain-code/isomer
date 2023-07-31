package com.isomer.messaging;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/30 14:35
 */
@SpringBootApplication
@EnableDubbo
public class IsomerMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsomerMessagingApplication.class, args);
    }
}
