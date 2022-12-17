package com.player.music.service;

import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.music.Entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IUserService {

    ResultEntity getUserData(String token);

    ResultEntity login(UserEntity userEntity);

    ResultEntity logout(String token);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);

    ResultEntity updateUser(UserEntity userEntity, String token);

    ResultEntity updatePassword(PasswordEntity passwordEntity, String token);

    ResultEntity updateAvater(String token, Map imgMap);
}
