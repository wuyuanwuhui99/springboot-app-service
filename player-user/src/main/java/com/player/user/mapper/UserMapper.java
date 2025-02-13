package com.player.user.mapper;

import com.player.common.entity.LogEntity;
import com.player.common.entity.UserEntity;
import com.player.user.entity.PasswordEntity;
import com.player.user.entity.ResetPasswordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * @author: wuwenqiang
     * @description: 查询用户数据
     * @date: 2021-6-16 22:06
     */
    UserEntity getUserData();

    /**
     * @author: wuwenqiang
     * @description: 查询用户数据
     * @date: 2021-6-16 22:06
     */
    UserEntity getMyUserData(String userId);

    UserEntity login(UserEntity userEntity);

    Long register(UserEntity userEntity);

    UserEntity queryUser(UserEntity userEntity);

    Long updateUser(UserEntity userEntity);

    Long updatePassword(PasswordEntity passwordEntity);

    Long log(LogEntity logEntity);

    Long updateAvater(String avater,String userId);

    List<UserEntity> vertifyUserByEmail(String email);

    Long resetPassword(ResetPasswordEntity resetPasswordEntity);

    UserEntity loginByEmail(String email);
}
