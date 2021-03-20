package com.player.music.service;

import com.player.common.entity.ResultEntity;
import com.player.music.Entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    ResultEntity getUserData(String token);

    ResultEntity login(String userId, String password);

    ResultEntity logout(HttpServletResponse response);

    ResultEntity register(UserEntity userEntity);

    ResultEntity findUser(String userId);

    ResultEntity updateUser(UserEntity resultEntity);

    ResultEntity updatePassword(String userId,String newPassword, String oldPassword);

    ResultEntity upload(String userId,String token,MultipartFile file);
}
