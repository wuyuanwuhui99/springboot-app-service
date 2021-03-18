package com.player.playermusic.controller;

import com.player.common.entity.ResultEntity;
import com.player.playermusic.service.imp.UserService;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/service")
@Api(value = "用户管理", description = "登录和退出登录和个人中心接口")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户登录信息")
    @GetMapping("/music/getUserData")
    public ResultEntity getUserData(HttpServletResponse response, @CookieValue(value = "token", required = false) String token) {
        return userService.getUserData(response, token);
    }

    @ApiOperation("登录")
    @PostMapping("/music/login")
    public ResultEntity login(HttpServletResponse response, @RequestBody Map userMap) {
        String userId = (String) userMap.get("userId");
        String password = (String) userMap.get("password");
        return userService.login(response, userId, password);
    }

    @ApiOperation("退出登录")
    @GetMapping("/music/logout")
    public ResultEntity logout(HttpServletResponse response) {
        return userService.logout(response);
    }

    @ApiOperation("注册时判断用户是否存在")
    @GetMapping("/music/findUser")
    public ResultEntity findUser(@Param("userId") String userId) {
        return userService.findUser(userId);
    }
}
