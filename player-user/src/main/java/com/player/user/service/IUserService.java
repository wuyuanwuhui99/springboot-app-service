package com.player.user.service;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;
import com.player.user.entity.PasswordEntity;

public interface IUserService {
    ResultEntity getUserData(String token);

    ResultEntity login(UserEntity userEntity);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);

    ResultEntity updateUser(UserEntity userEntity,String token);

    ResultEntity updatePassword(PasswordEntity passwordEntity, String token);
}
