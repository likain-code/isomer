package com.isomer.redis.core;

import com.isomer.redis.exception.ExpireNoExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author likain
 * @since 2023/7/28 12:37
 */
@Service
public class RedisService<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    public V getValue(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValue(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(K key, V value, long expire, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expire, unit);
    }

    public void deleteKey(K key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(K key) {
        Boolean b = redisTemplate.hasKey(key);
        return b != null && b;
    }

    public long getExpire(K key, TimeUnit unit) {
        Long expire = redisTemplate.getExpire(key, unit);
        return expire == null ? -1L : expire;
    }

    public void setExpire(K key, long expire, TimeUnit unit) {
        redisTemplate.expire(key, expire, unit);
    }

    public void addExpire(K key, long extra, TimeUnit unit) {
        long oldExp = this.getExpire(key, unit);
        if (oldExp == -1) {
            throw new ExpireNoExistException("There is no expire for key: [" + key + "]");
        }

        long newExp = oldExp + extra;
        this.setExpire(key, newExp, unit);
    }
}
