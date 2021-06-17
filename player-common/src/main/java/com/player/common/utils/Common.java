package com.player.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static String getFullTime(String pattern){
        //得到long类型当前时间
        long  l = System.currentTimeMillis();
        //new日期对象
        Date date =  new  Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat;
        if(pattern == null){
            dateFormat = new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        }else{
            dateFormat = new  SimpleDateFormat(pattern);
        }
        return dateFormat.format(date);
    }

    public static RequestEntity postRequestEntity(String path,String token, Object params){
        URI uri = UriComponentsBuilder.fromUriString(path).build().toUri();
        // 自定义body实体类
        String s = JSON.toJSONString(params);
        return RequestEntity.post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Authorization", token)
                .body(s);
    }

    public static RequestEntity putRequestEntity(String path,String token, Object params){
        URI uri = UriComponentsBuilder.fromUriString(path).build().toUri();
        // 自定义body实体类
        String s = JSON.toJSONString(params);
        return RequestEntity.put(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Authorization", token)
                .body(s);
    }
}
