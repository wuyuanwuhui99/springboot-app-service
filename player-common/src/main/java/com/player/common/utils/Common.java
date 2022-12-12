package com.player.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
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

    public static RequestEntity deleteRequestEntity(String path,String token){
        URI uri = UriComponentsBuilder.fromUriString(path).build().toUri();
        // 自定义body实体类
        return RequestEntity.delete(uri)
                .accept(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json; charset=UTF-8")
                .header("Authorization", token)
                .build();
    }

    public static String nullToString(String str) {
        return str == null ?  "" : str;
    }

    /**
     * 为文件重新命名，命名规则为当前系统时间毫秒数
     *
     * @return string
     */
    private static String getFileNameNew() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }

    public static Boolean generateImage(String base64str,String savepath){
        if (base64str == null)return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(base64str);
            for(int i=0;i<b.length;++i){
                if(b[i]<0){
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(savepath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
