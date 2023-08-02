package com.isomer.common.utils;

import java.util.UUID;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/2 15:48
 */
public class SecretUtil {

    public static String gen() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
