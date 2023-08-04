package com.isomer.common.utils;

/**
 * Description:
 *
 * @author likain
 * @since 2023/8/4 10:34
 */
public class MqttTopicUtil {

    public static String genUpLink(Integer tag, Long id) {
        return String.format("%s/%s/%s/%s", "isomer", "up", tag, id);
    }

    public static String genDownLink(Integer tag, Long id) {
        return String.format("%s/%s/%s/%s", "isomer", "down", tag, id);
    }
}
