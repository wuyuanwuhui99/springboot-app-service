package com.player.music.service;

import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.music.Entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    ResultEntity getUserData(String token);

    ResultEntity login(UserEntity userEntity);

    ResultEntity logout(HttpServletResponse response);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);

    ResultEntity updateUser(UserEntity userEntity, String token);

    ResultEntity updatePassword(PasswordEntity passwordEntity, String token);

    ResultEntity upload(String userId,String token,MultipartFile file);
}
