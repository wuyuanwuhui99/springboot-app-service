package com.player.user.service;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.UserEntity;

public interface IUserService {
    ResultEntity getUserData(String token);

    ResultEntity login(UserEntity userEntity);

    ResultEntity register(UserEntity userEntity);

    ResultEntity getUserById(String userId);
}
