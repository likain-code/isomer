package com.isomer.redis.exception;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 13:05
 */
public class ExpireNoExistException extends RedisServiceException {

    public ExpireNoExistException(String cause) {
        super(cause);
    }
}
