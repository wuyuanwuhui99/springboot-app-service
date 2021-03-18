package com.player.music.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtils {

    private static String cookieName;
    private static int expiry;

    @Value("${cookie.name}")
    public void  setCookieName(String cookieName){
        this.cookieName = cookieName;
    }

    @Value("${cookie.expiry}")
    public void setExpiry(int expiry){
        this.expiry = expiry;
    }

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
