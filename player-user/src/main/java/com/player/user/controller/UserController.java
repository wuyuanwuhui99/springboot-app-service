package com.player.user.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.common.myInterface.OperLog;
import com.player.common.utils.OperationType;
import com.player.user.entity.MailEntity;
import com.player.user.entity.PasswordEntity;
import com.player.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultEntity getUserData(@RequestHeader(required = false,value = "Authorization") String token) {
        return userService.getUserData(token);
    }

    @OperLog(message = "登录校验", operation = OperationType.LOGIN)
    @ApiOperation("登录校验")
    @PostMapping("/user/login")
    public ResultEntity login(@RequestBody UserEntity userEntity) {
        return userService.login(userEntity);
    }

    @OperLog(message = "退出登录", operation = OperationType.LOGOUT)
    @ApiOperation("退出登录")
    @PostMapping("/music/logout")
    public ResultEntity logout(@RequestHeader("Authorization") String token) {
        return userService.logout(token);
    }


    @OperLog(message = "注册", operation = OperationType.QUERY)
    @ApiOperation("注册,请求地地址：/service/user/register")
    @PostMapping("/user/register")
    public ResultEntity register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    @OperLog(message = "查询用户是否存在", operation = OperationType.QUERY)
    @ApiOperation("查询用户是否存在,请求地地址：/service/user/vertifyUser")
    @PostMapping("/user/vertifyUser")
    public ResultEntity vertifyUser(@RequestBody UserEntity userEntity) {
        return userService.vertifyUser(userEntity);
    }

    @OperLog(message = "更新用户信息", operation = OperationType.UPDATE)
    @ApiOperation("更新用户信息")
    @PutMapping("/user/updateUser")
    public ResultEntity updateUser(@RequestHeader("Authorization") String token,@RequestBody UserEntity userEntity,HttpServletRequest request) {
        return userService.updateUser(userEntity,token);
    }

    @ApiOperation("修改密码")
    @PutMapping("/user/updatePassword")
    public ResultEntity updatePassword(@RequestHeader("Authorization") String token,@RequestBody PasswordEntity passwordEntity,HttpServletRequest request) {
        return userService.updatePassword(passwordEntity,token);
    }

    @ApiOperation("头像上传")
    @PutMapping("/user/updateAvater")
    public ResultEntity updateAvater(@RequestHeader("Authorization") String token, @RequestBody Map map) {
        return userService.updateAvater(token,map.get("img").toString());
    }

    @ApiOperation("找回密码")
    @PostMapping("/user/getBackPassword")
    public ResultEntity getBackPassword(@RequestHeader("Authorization") String token, @RequestBody Map map) {
        return userService.getBackPassword(token,map.get("email").toString());
    }

    @ApiOperation("发送邮件")
    @PostMapping("/user/SendSimpleMessage")
    public void SendSimpleMessage(@RequestBody MailEntity mailRequest) {
        userService.sendSimpleMail(mailRequest);
    }
}
