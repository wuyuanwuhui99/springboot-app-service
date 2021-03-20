package com.player.music.service.imp;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.utils.JwtToken;
import com.player.music.Entity.UserEntity;
import com.player.music.dao.UserDao;
import com.player.music.service.IUserService;
import com.player.music.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements IUserService {

    private JwtToken jwtToken = new JwtToken();

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author: wuwenqiang
     * @methodsName: findUserByUserId
     * @description: 查找用户
     * @param: userId 用户id
     * @return: ResultEntity
     * @date: 2020-07-25 9:30
     */
    @Override
    public ResultEntity findUserByUserId(String userId) {
        Optional<UserEntity> userEntityOptional = userDao.findById(userId);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return ResultUtil.success(userEntity);
        }
        return null;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getUserData
     * @description: 查找用户
     * @param: userId 用户id
     * @return: ResultEntity
     * @date: 2020-08-11 22:30
     */
    @Override
    public ResultEntity getUserData(HttpServletResponse response, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(
                "http://player-movie/service/movie/getUserData",
                HttpMethod.GET,
                new HttpEntity<String>(headers),ResultEntity.class
        );
        CookieUtils.setTokenCookie(response,jwtToken.createToken(responseEntity.getBody().getData()));
        return  responseEntity.getBody();
    }

    /**
     * @author: wuwenqiang
     * @methodsName: login
     * @description: 登录
     * @param: userId 用户id
     * @param: password 用户密码
     * @return: ResultEntity
     * @date: 2020-08-11 23:54
     */
    @Override
    public ResultEntity login(HttpServletResponse response, String userId, String password) {
        List<Map<String, Object>> userEntities = userDao.findByUserIdAndPassword(userId, password);
        if (userEntities.size() > 0) {
            Map userEntitiyMap = userEntities.get(0);
            Cookie cookie = new Cookie("userId", (String) userEntitiyMap.get("userId"));
            cookie.setMaxAge(365 * 24 * 60 * 60); // 365天过期
            cookie.setHttpOnly(true);//不能被js访问的Cookie
            response.addCookie(cookie);//将cookie对象加入response响应
            return ResultUtil.success(userEntitiyMap);
        }
        return null;
    }

    /**
     * @author: wuwenqiang
     * @methodsName: logout
     * @description: 退出登录
     * @return: ResultEntity
     * @date: 2020-08-11 23:54
     */
    @Override
    public ResultEntity logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResultUtil.success(null, "退出登录成功");
    }

    /**
     * @author: wuwenqiang
     * @methodsName: register
     * @description: 注册
     * @return: ResultEntity
     * @date: 2020-08-11 23:54
     */
    @Override
    @Transactional
    public ResultEntity register(UserEntity userEntity) {
        UserEntity userEntity1 = userDao.saveAndFlush(userEntity);
        return ResultUtil.success(userEntity1, "注册成功");
    }

    /**
     * @author: wuwenqiang
     * @methodsName: findUser
     * @description: 判断用户是否存在
     * @return: ResultEntity
     * @date: 2020-08-11 23:54
     */
    @Override
    public ResultEntity findUser(String userId) {
        List<Map<String, Object>> userEntities = userDao.findByUserId(userId);
        if (userEntities.size() > 0) {
            return ResultUtil.fail(null, "用户已经存在");
        } else {
            return ResultUtil.success(null, "");
        }
    }
}
