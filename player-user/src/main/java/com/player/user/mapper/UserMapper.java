package com.player.user.mapper;

import com.player.common.entity.UserEntity;

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

    Long register(UserEntity userEntity);

    UserEntity getUserById(String userId);
}
