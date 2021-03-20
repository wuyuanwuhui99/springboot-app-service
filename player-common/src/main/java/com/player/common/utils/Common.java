package com.player.common.utils;

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
}
