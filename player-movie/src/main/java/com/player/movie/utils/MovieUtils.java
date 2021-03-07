package com.player.movie.utils;

import javax.servlet.http.HttpServletRequest;

public class MovieUtils {
    public static String getPath(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        return requestURI + "?" + queryString;
    }
}
