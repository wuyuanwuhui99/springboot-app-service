package com.player.music.utils;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUitls {


    public static ResultEntity getRedis(RedisTemplate redisTemplate,String url, String result,String errorMsg){
        Map<String,Object> resultMap = JSON.parseObject(result, Map.class);
        Integer code = (Integer) resultMap.get("code");
        ResultEntity resultEntity;
        if( code == 0){
            resultEntity = ResultUtil.success(resultMap.get("data"));
        }else{
            resultEntity = ResultUtil.fail(null, errorMsg);
        }
        redisTemplate.opsForValue().set(url,JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
        return resultEntity;
    }
}
