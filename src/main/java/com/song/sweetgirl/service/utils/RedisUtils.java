package com.song.sweetgirl.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String getCache(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public Boolean setCache(final String key, String value) {
        Boolean result = Boolean.FALSE;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public Boolean refreshCache(final String key, String value) {
        Boolean result = Boolean.FALSE;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public Boolean deleteCache(final String key) {
        Boolean result = Boolean.FALSE;
        try {
            redisTemplate.delete(key);
            result = Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
