package com.player.user.service.imp;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.common.utils.ResultCode;
import com.player.user.mapper.UserMapper;
import com.player.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserService implements IUserService {
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
            return ResultUtil.success(resultUserEntity, "登录成功", token);
        } else {
            return ResultUtil.fail(null, "登录失败，账号或密码错误", ResultCode.LOGOUT);
        }
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
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity getUserById(String userId) {
        return ResultUtil.success(userMapper.getUserById(userId));
    }
}
