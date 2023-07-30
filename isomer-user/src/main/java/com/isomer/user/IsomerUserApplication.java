package com.isomer.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 10:17
 */
@SpringBootApplication
@MapperScan({"com.isomer.user.mapper"})
public class IsomerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsomerUserApplication.class, args);
    }
}
