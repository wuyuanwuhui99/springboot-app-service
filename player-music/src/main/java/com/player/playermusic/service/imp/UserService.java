package com.player.playermusic.service.imp;

import com.player.playermusic.Entity.ResultEntity;
import com.player.playermusic.Entity.UserEntity;
import com.player.playermusic.dao.UserDao;
import com.player.playermusic.service.IUserService;
import com.player.playermusic.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;

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
    public ResultEntity getUserData(HttpServletResponse response, String userId) {
        if (StringUtils.isNotEmpty(userId)) {
            List<Map<String, Object>> userEntities = userDao.findByUserId(userId);
            if (userEntities.size() > 0) {
                return ResultUtil.success(userEntities.get(0));
            } else {
                List<Map<String, Object>> roles = userDao.findAllByRole("public");
                Random random = new Random();
                int nextInt = random.nextInt(roles.size());
                return ResultUtil.success(roles.get(nextInt));
            }
        } else {//如果没有登录，随机挑选一个公共账号
            List<Map<String, Object>> roles = userDao.findAllByRole("public");
            Random random = new Random();
            int nextInt = random.nextInt(roles.size());
            Map<String, Object> map = roles.get(nextInt);
            Cookie cookie = new Cookie("userId", (String) map.get("userId"));
            cookie.setMaxAge(365 * 24 * 60 * 60); // 365天过期
            cookie.setHttpOnly(true);//不能被js访问的Cookie
            response.addCookie(cookie);//将cookie对象加入response响应
            return ResultUtil.success(roles.get(nextInt));
        }
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
