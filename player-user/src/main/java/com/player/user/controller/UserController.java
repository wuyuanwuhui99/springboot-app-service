package com.player.user.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.JwtToken;
import com.player.common.utils.OperationType;
import com.player.user.entity.PasswordEntity;
import com.player.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        return userService.getUserData(request.getHeader("Authorization"));
    }

    @OperLog(message = "登录校验", operation = OperationType.LOGIN)
    @ApiOperation("登录校验")
    @PostMapping("/user/login")
    public ResultEntity login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

    @OperLog(message = "注册", operation = OperationType.QUERY)
    @ApiOperation("注册,请求地地址：/service/user/register")
    @PutMapping("/user/register")
    public ResultEntity register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    @OperLog(message = "查询用户是否存在", operation = OperationType.QUERY)
    @ApiOperation("查询用户是否存在,请求地地址：/service/movie/getUserById")
    @GetMapping("/user/getUserById")
    public ResultEntity getUserById(@RequestParam("userId")String userId) {
        return userService.getUserById(userId);
    }

    @OperLog(message = "更新用户信息", operation = OperationType.UPDATE)
    @ApiOperation("更新用户信息")
    @PutMapping("/user-getway/updateUser")
    public ResultEntity updateUser(@RequestBody UserEntity userEntity,HttpServletRequest request) {
        return userService.updateUser(userEntity,request.getHeader("Authorization"));
    }

    @ApiOperation("修改密码")
    @PutMapping("/user-getway/updatePassword")
    public ResultEntity updatePassword(@RequestBody PasswordEntity passwordEntity,HttpServletRequest request) {
        return userService.updatePassword(passwordEntity,request.getHeader("Authorization"));
    }

    @ApiOperation("修改密码")
    @PostMapping("/user-getway/upload")
    public ResultEntity upload(HttpServletRequest request, @RequestParam("img") MultipartFile file) {
        String token = request.getHeader("Authorization");
        String userId = JwtToken.getUserId(token);
        return userService.upload(userId,token,file);
    }
}