package com.player.music.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.player.common.entity.UserEntity;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class UserUtils {

    @Autowired
    private JwtToken jwtToken;

    /**
     * @author: wuwenqiang
     * @methodsName: fail
     * @description: 获取cookie方法
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    public static String getUserId(token) {
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(userEntity != null){
            return userEntity.getUserId();
        }
        return null;
    }
}
