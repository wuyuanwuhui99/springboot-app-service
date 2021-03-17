package com.player.playermusic.utils;

import com.player.playermusic.Entity.ResultEntity;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    @Value("${cookie.name}")
    private static String cookieName;

    @Value("${cookie.expiry}")
    private static Integer expiry;

    /**
     * @author: wuwenqiang
     * @methodsName: fail
     * @description: 获取cookie方法
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    public static void setTokenCookie(HttpServletResponse response, String value) {
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }
}
