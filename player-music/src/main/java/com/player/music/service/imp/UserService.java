package com.player.music.service.imp;

import com.player.common.entity.PasswordEntity;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import com.player.music.Entity.UserEntity;
import com.player.music.dao.UserDao;
import com.player.music.service.IUserService;
import com.player.music.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService implements IUserService {

    @Value("${app.avater-path}")
    private String avaterPath;

    @Value("${app.avater-img}")
    private String avaterImg;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author: wuwenqiang
     * @methodsName: getUserData
     * @description: 查找用户
     * @param: userId 用户id
     * @return: ResultEntity
     * @date: 2020-08-11 22:30
     */
    @Override
    public ResultEntity getUserData(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        ResponseEntity<ResultEntity> responseEntity = restTemplate.exchange(
                "http://player-user/service/user/getUserData",
                HttpMethod.GET,
                new HttpEntity<String>(headers),ResultEntity.class
        );
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
    public ResultEntity login(UserEntity userEntity) {
        return restTemplate.exchange(
                Common.postRequestEntity("http://player-user/service/user/login",null,userEntity),
                ResultEntity.class
        ).getBody();
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
        return restTemplate.exchange(Common.postRequestEntity("http://player-user/service/user/register",null,userEntity),ResultEntity.class).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 查询单个用户，用于校验用户是否存在
     * @date: 2021-01-01 23:39
     */
    @Override
    public ResultEntity getUserById(String userId) {
        return restTemplate.exchange(
                "http://player-user/service/user/getUserById?userId="+userId,
                HttpMethod.GET,
                new HttpEntity<String>(new HttpHeaders()),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @description: 更新用户信息
     * @date: 2020-12-24 22:40
     */
    @Override
    public ResultEntity updateUser(UserEntity userEntity, String token) {
        return restTemplate.exchange(
                Common.putRequestEntity("http://player-user/service/user-getway/updateUser",token,userEntity),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @methodsName: updatePassword
     * @description: 修改密码
     * @return: ResultEntity
     * @date: 2020-08-11 23:54
     */
    @Override
    public ResultEntity updatePassword(PasswordEntity passwordEntity, String token) {
        return restTemplate.exchange(
                Common.postRequestEntity("http://player-user/service/user-getway/updatePassword",token,passwordEntity),
                ResultEntity.class
        ).getBody();
    }

    /**
     * @author: wuwenqiang
     * @methodsName: updatePassword
     * @description: 修改密码
     * @return: ResultEntity
     * @date: 2020-08-11 23:54
     */
    @Override
    public ResultEntity upload(String userId,String token,MultipartFile file){
        if (file.isEmpty()) {
            return ResultUtil.fail("请选择文件");
        }
        String fileName = file.getOriginalFilename();
        String myFileName = userId + "_" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(avaterPath + myFileName);
        String date = Common.getFullTime(null);
        try {
            file.transferTo(dest);
            userDao.updateAvater(avaterImg + myFileName, date, userId);
            ResultEntity resultEntity = getUserData(token);
            resultEntity.getMsg("上传成功");
            return resultEntity;
        } catch (IOException e) {
            return ResultUtil.fail(e,"上传失败");
        }
    }
}
