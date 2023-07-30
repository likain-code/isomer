package com.isomer.redis.exception;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 12:59
 */
public class RedisServiceException extends RuntimeException {

    protected RedisServiceException(String cause) {
        super(cause);
    }
}
