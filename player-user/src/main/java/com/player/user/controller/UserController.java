package com.player.user.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.OperationType;
import com.player.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/service")
@Api(value = "用户增删改查操作", description = "用户增删改查操作")
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @OperLog(message = "查询用户信息", operation = OperationType.QUERY)
    @ApiOperation("查询用户信息")
    @GetMapping("/user/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return userService.getUserData(token);
    }

    @OperLog(message = "登录校验", operation = OperationType.LOGIN)
    @ApiOperation("登录校验")
    @PostMapping("/user/login")
    public ResultEntity login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }
}
