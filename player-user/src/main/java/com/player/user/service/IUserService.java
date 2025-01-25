package com.player.user.service;

import com.player.common.entity.LogEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.user.entity.MailEntity;
import com.player.user.entity.PasswordEntity;
import com.player.user.entity.ResetPasswordEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    ResultEntity getUserData(String token);

    ResultEntity login(UserEntity userEntity);

    ResultEntity logout(String token);

    ResultEntity register(UserEntity userEntity);

    ResultEntity vertifyUser(UserEntity userEntity);

    ResultEntity updateUser(UserEntity userEntity,String token);

    ResultEntity updatePassword(PasswordEntity passwordEntity, String token);

    ResultEntity updateAvater(String token, String base64);

    ResultEntity getBackPasswordByEmail(MailEntity mailRequest);

    ResultEntity resetPassword(ResetPasswordEntity resetPasswordEntity);
}
