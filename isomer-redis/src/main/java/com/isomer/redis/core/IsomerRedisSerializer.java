package com.isomer.redis.core;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 13:13
 */
public class IsomerRedisSerializer<T> extends Jackson2JsonRedisSerializer<T> {

    public IsomerRedisSerializer(Class<T> type) {
        super(type);
    }
}
