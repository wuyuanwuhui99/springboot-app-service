package com.player.playermusic.config;

import com.alibaba.fastjson.JSONObject;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] paths = {"queryFavorite","addFavorite","deleteFavorite","getFavorite"};
        boolean verification = false;
        for(int i = 0; i < paths.length; i++){
            String path = request.getServletPath();
            if(path.indexOf(paths[i]) != -1){
                verification = true;
                break;
            }
        }
        if(verification){
            String token = request.getHeader("Athorization");
            if (token == null) {
                renderJson(response, ResultUtil.fail("未通过登录认证", null, ResultCode.LOGOUT));
                return false;
            }
            UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
            if (userEntity == null) {
                response.setContentType("application/json;charset=UTF-8");
                //设置编码格式
                response.setCharacterEncoding("UTF-8");
                response.setStatus(401);
                response.getWriter().write("未通过登录认证，请在登录页面登录");
                renderJson(response, ResultUtil.fail("未通过登录认证", ResultCode.LOGOUT));
                return false;
            }
            return true;
        }else {
            return true;
        }
    }

    protected void renderJson(HttpServletResponse response, ResultEntity resultEntity) {
        String dataJson = JSONObject.toJSONString(resultEntity);
        PrintWriter writer = null;
        try {
            response.setContentType("application/json; charset=utf-8");
            writer = response.getWriter();
            writer.write(dataJson);
            writer.flush();
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
