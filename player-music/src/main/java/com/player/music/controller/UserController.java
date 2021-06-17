package com.player.music.controller;

import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.JwtToken;
import com.player.common.utils.OperationType;
import com.player.music.Entity.UserEntity;
import com.player.music.service.IUserService;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/service")
@Api(value = "用户管理", description = "登录和退出登录和个人中心接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("获取用户登录信息")
    @GetMapping("/music/getUserData")
    public ResultEntity getUserData(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return userService.getUserData(token);
    }

    @ApiOperation("登录")
    @PostMapping("/music/login")
    public ResultEntity login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

    @ApiOperation("退出登录")
    @GetMapping("/music/logout")
    public ResultEntity logout(HttpServletResponse response) {
        return userService.logout(response);
    }

    @ApiOperation("注册时判断用户是否存在")
    @GetMapping("/music/getUserById")
    public ResultEntity getUserById(@Param("userId") String userId) {
        return userService.getUserById(userId);
    }

    @OperLog(message = "更新用户信息", operation = OperationType.UPDATE)
    @ApiOperation("更新用户信息")
    @PutMapping("/music-getway/updateUser")
    public ResultEntity updateUser(@RequestBody UserEntity userEntity, HttpServletRequest request) {
        return userService.updateUser(userEntity,request.getHeader("Authorization"));
    }

    @ApiOperation("修改密码")
    @PutMapping("/music-getway/updatePassword")
    public ResultEntity updatePassword(@RequestBody PasswordEntity passwordEntity, HttpServletRequest request) {
        return userService.updatePassword(passwordEntity,request.getHeader("Authorization"));
    }

    @ApiOperation("修改密码")
    @PostMapping("/music-getway/upload")
    public ResultEntity upload(HttpServletRequest request, @RequestParam("img") MultipartFile file) {
        String token = request.getHeader("Authorization");
        String userId = JwtToken.getUserId(token);
        return userService.upload(userId,token,file);
    }
}
