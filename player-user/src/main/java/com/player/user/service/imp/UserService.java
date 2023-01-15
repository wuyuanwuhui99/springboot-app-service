package com.player.user.service.imp;

import com.player.common.entity.LogEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import com.player.common.utils.ResultCode;
import com.player.user.entity.PasswordEntity;
import com.player.user.mapper.UserMapper;
import com.player.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${app.avater-path}")
    private String avaterPath;

    @Value("${app.avater-img}")
    private String avaterImg;

    @Autowired
    private UserMapper userMapper;
    /**
     * @author: wuwenqiang
     * @description: 获取用户数据
     * @date: 2021-06-16 22:50
     */
    @Override
    public ResultEntity getUserData(String token) {
        UserEntity userEntity = null;
        if (token == null || StringUtils.isEmpty(token)) {
            userEntity = userMapper.getUserData();//如果用户签名为空，随机从数据库中查询一个公共的账号
        } else {
            userEntity = JwtToken.parserToken(token, UserEntity.class);
            if (userEntity == null) {//如果用户签名为空，随机从数据库中查询一个公共的账号
                userEntity = userMapper.getUserData();
            }else{
                userEntity = userMapper.getMyUserData(userEntity.getUserId());
            }
        }
        String newToken = JwtToken.createToken(userEntity);
        redisTemplate.opsForValue().set(newToken, "1",30, TimeUnit.DAYS);
        return ResultUtil.success(userEntity, null, newToken);
    }

    /**
     * @author: wuwenqiang
     * @description: 登录校验
     * @date: 2020-12-25 00:04
     */
    @Override
    public ResultEntity login(UserEntity userEntity) {
        UserEntity resultUserEntity = userMapper.login(userEntity);
        if (resultUserEntity != null) {
            String token = JwtToken.createToken(resultUserEntity);//token有效期一天
            redisTemplate.opsForValue().set(token, "1",30, TimeUnit.DAYS);
            return ResultUtil.success(resultUserEntity, "登录成功", token);
        } else {
            return ResultUtil.fail(null, "登录失败，账号或密码错误", ResultCode.FAIIL);
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 退出登录
     * @date: 2020-12-25 00:04
     */
    @Override
    public ResultEntity logout(String token) {
        redisTemplate.delete(token);
        return ResultUtil.success(null, "退出登录成功", token);
    }

    /**
     * @author: wuwenqiang
     * @description: 注册
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity register(UserEntity userEntity) {
        userEntity.setCreateDate(new Date());
        userEntity.setUpdateDate(new Date());
        Long rows = userMapper.register(userEntity);
        if (rows >= 1) {
            return ResultUtil.success(userMapper.getUserById(userEntity.getUserId()));
        }
        return ResultUtil.fail(null, "注册失败");
    }

    /**
     * @author: wuwenqiang
     * @description: 查询单个用户，用于校验用户是否存在
     * @date: 2021-06-17 22:33
     */
    @Override
    public ResultEntity getUserById(String userId) {
        return ResultUtil.success(userMapper.getUserById(userId));
    }

    /**
     * @author: wuwenqiang
     * @description: 更新用户信息
     * @date:  2021-06-17 22:33
     */
    @Override
    public ResultEntity updateUser(UserEntity userEntity,String token) {
        if(!userEntity.getUserId().equals(JwtToken.parserToken(token, UserEntity.class).getUserId())){
            return ResultUtil.fail(null,"禁止修改其他用户信息");
        }
        return ResultUtil.success(userMapper.updateUser(userEntity));
    }

    /**
     * @author: wuwenqiang
     * @description: 修改密码
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity updatePassword(PasswordEntity passwordEntity, String token) {
        if(passwordEntity.getUserId() != JwtToken.parserToken(token, UserEntity.class).getUserId()){
            return ResultUtil.fail(null,"禁止修改其他用户密码");
        }
        return ResultUtil.success(userMapper.updatePassword(passwordEntity));
    }

    /**
     * @author: wuwenqiang
     * @methodsName: updatePassword
     * @description: 修改密码
     * @return: ResultEntity
     * @date: 2021-06-18 00:21
     */
    @Override
    public ResultEntity updateAvater(String token, String base64){
        String userId = JwtToken.getUserId(token);
        if ("".equals(base64) || base64 == null) {
            return ResultUtil.fail("请选择文件");
        }
        String ext = base64.replaceAll(";base64,.+","").replaceAll("data:image/","");

        base64 = base64.replaceAll("data:image/.+base64,","");
        String imgName = UUID.randomUUID().toString().replace("-", "") + "." + ext;
        String savePath = avaterPath+ imgName;
        String newImgName = Common.generateImage(base64, savePath);
        if(newImgName != null){
            userMapper.updateAvater(newImgName,userId);
            return ResultUtil.success(newImgName);
        }else{
            return ResultUtil.fail("修改头像失败");
        }
    }
}
