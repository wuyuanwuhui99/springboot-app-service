package com.player.circle.config;

import com.alibaba.fastjson.JSONObject;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthInterceptor implements HandlerInterceptor {
    @Value("${token.secret}")
    private String secret;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || redisTemplate.opsForValue().get(token) == null) {
            renderJson(response, ResultUtil.fail("未通过登录认证", null, ResultCode.LOGOUT));
            return false;
        }
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class,secret);
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
    }

    protected void renderJson(HttpServletResponse response, ResultEntity resultEntity) {
        String dataJson = JSONObject.toJSONString(resultEntity);
        PrintWriter writer;
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
