package com.player.toutiao.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component //注入spring容器
public class RedisCacheUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public void set(String key,String value) {
        if( key != null)redisTemplate.opsForValue().set(key,value);
    }
}