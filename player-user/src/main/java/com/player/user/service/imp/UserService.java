package com.player.user.service.imp;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import com.player.common.utils.ResultCode;
import com.player.user.entity.MailEntity;
import com.player.user.entity.PasswordEntity;
import com.player.user.entity.ResetPasswordEntity;
import com.player.user.mapper.UserMapper;
import com.player.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService {
    @Autowired
    private RedisTemplate redisTemplate;

    //注入邮件工具类
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${token.secret}")
    public String secret;

    @Value("${app.avater-path}")
    private String avaterPath;

    @Value("${app.avater-img}")
    private String avaterImg;

    @Value("${spring.mail.username}")
    private String sendMailer;

    @Autowired
    private UserMapper userMapper;
    /**
     * @author: wuwenqiang
     * @description: 获取用户数据
     * @date: 2021-06-16 22:50
     */
    @Override
    public ResultEntity getUserData(String token) {
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class,secret);
        if (token == null || StringUtils.isEmpty(token) || userEntity == null) {
            userEntity = userMapper.getUserData();//如果用户签名为空，随机从数据库中查询一个公共的账号
        } else {
            userEntity = userMapper.getMyUserData(userEntity.getUserAccount());
        }
        String newToken = JwtToken.createToken(userEntity,secret);
        redisTemplate.opsForValue().set(newToken, "1",30, TimeUnit.DAYS);
        return ResultUtil.success(userEntity, null, newToken);
    }

    /**
     * @author: wuwenqiang
     * @description: 登录校验
     * @date: 2020-12-25 00:04
     */
    @Override
    public ResultEntity login(UserEntity userEntity) {
        UserEntity resultUserEntity = userMapper.login(userEntity);
        if (resultUserEntity != null) {
            String token = JwtToken.createToken(resultUserEntity,secret);//token有效期30天
            redisTemplate.opsForValue().set(token, "1",30, TimeUnit.DAYS);
            return ResultUtil.success(resultUserEntity, "登录成功", token);
        } else {
            return ResultUtil.fail(null, "登录失败，账号或密码错误", ResultCode.FAIL);
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 退出登录
     * @date: 2020-12-25 00:04
     */
    @Override
    public ResultEntity logout(String token) {
        redisTemplate.delete(token);
        return ResultUtil.success(null, "退出登录成功", token);
    }

    /**
     * @author: wuwenqiang
     * @description: 注册
     * @date: 2021-01-01 23:39
     */
    @Override
    @Transactional
    public ResultEntity register(UserEntity userEntity) {
        userEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        Long row = userMapper.register(userEntity);
        if (row > 0 ) {
            UserEntity userEntity1 = userMapper.queryUser(userEntity);
            String newToken = JwtToken.createToken(userEntity1,secret);
            redisTemplate.opsForValue().set(newToken, "1",30, TimeUnit.DAYS);
            return ResultUtil.success(userEntity1, null, newToken);
        }
        return ResultUtil.fail(null, "注册失败");
    }

    /**
     * @author: wuwenqiang
     * @description: 查询单个用户，用于校验用户是否存在
     * @date: 2021-06-17 22:33
     */
    @Override
    public ResultEntity vertifyUser(UserEntity userEntity) {
        UserEntity mUserEntity = userMapper.queryUser(userEntity);
        if(mUserEntity != null){
            if(mUserEntity.getUserAccount().equals(userEntity.getUserAccount())){
                return ResultUtil.success(1,"账号已存在");
            }else {
                return ResultUtil.success(1,"邮箱已存在");
            }
        }else{
            return ResultUtil.success(0);
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 更新用户信息
     * @date:  2021-06-17 22:33
     */
    @Override
    @Transactional
    public ResultEntity updateUser(UserEntity userEntity,String token) {
        String userId = JwtToken.parserToken(token, UserEntity.class,secret).getUserAccount();
        userEntity.setUserAccount(userId);
        return ResultUtil.success(userMapper.updateUser(userEntity));
    }

    /**
     * @author: wuwenqiang
     * @description: 修改密码
     * @date: 2020-12-24 22:40
     */
    @Override
    @Transactional
    public ResultEntity updatePassword(PasswordEntity passwordEntity, String token) {
        passwordEntity.setUserId(JwtToken.parserToken(token, UserEntity.class,secret).getUserAccount());
        Long row = userMapper.updatePassword(passwordEntity);
        if(row > 0){
            return ResultUtil.success(row,"修改密码成功");
        }
        return ResultUtil.fail(0,"旧密码错误");
    }

    /**
     * @author: wuwenqiang
     * @methodsName: updatePassword
     * @description: 修改密码
     * @return: ResultEntity
     * @date: 2021-06-18 00:21
     */
    @Override
    @Transactional
    public ResultEntity updateAvater(String token, String base64){
        String userId = JwtToken.getId(token,secret);
        if ("".equals(base64) || base64 == null) {
            return ResultUtil.fail("请选择文件");
        }
        String ext = base64.replaceAll(";base64,.+","").replaceAll("data:image/","");

        base64 = base64.replaceAll("data:image/.+base64,","");
        String imgName = UUID.randomUUID().toString().replace("-", "") + "." + ext;
        String savePath = avaterPath+ imgName;
        String newImgName = Common.generateImage(base64, savePath);
        if(newImgName != null){
            userMapper.updateAvater(newImgName,userId);
            return ResultUtil.success(newImgName);
        }else{
            return ResultUtil.fail("修改头像失败");
        }
    }

    /**
     * @author: wuwenqiang
     * @methodsName: sendSimpleMail
     * @description: 发送文本邮件
     * @return: ResultEntity
     * @date: 2025-01-23 21:42
     */
    @Override
    public ResultEntity sendEmailVertifyCode(MailEntity mailRequest){
        if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", mailRequest.getEmail())){
            return ResultUtil.fail(null,"邮箱格式错误");
        }else if(userMapper.vertifyUserByEmail(mailRequest.getEmail()).size() == 0){
            return  ResultUtil.fail(null,"该账号不存在");
        }
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        mailRequest.setText("验证码：" + code + "，请在五分钟内完成操作");
        redisTemplate.opsForValue().set(mailRequest.getEmail(), code,5, TimeUnit.MINUTES);
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发件人
        message.setFrom(sendMailer);
        //邮件收件人 1或多个
        message.setTo(mailRequest.getEmail());
        //邮件主题
        message.setSubject("验证码");
        //邮件内容
        message.setText(mailRequest.getText());
        //邮件发送时间
        message.setSentDate(new Date());

        javaMailSender.send(message);

        return ResultUtil.success(1,"验证码已发送到邮箱，请五分钟内完成操作");
    }

    /**
     * @author: wuwenqiang
     * @methodsName: resetPassword
     * @description: 发送文本邮件
     * @return: ResultEntity
     * @date: 2025-01-23 21:42
     */
    @Override
    @Transactional
    public ResultEntity resetPassword(ResetPasswordEntity resetPasswordEntity){
        int code = (int)redisTemplate.opsForValue().get(resetPasswordEntity.getEmail());
        if(code != resetPasswordEntity.getCode()){
            return ResultUtil.fail(null,"验证码无效");
        }else{
            userMapper.resetPassword(resetPasswordEntity);
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(resetPasswordEntity.getEmail());
            userEntity.setPassword(resetPasswordEntity.getPassword());
            UserEntity mUserEntity = userMapper.login(userEntity);
            String token = JwtToken.createToken(mUserEntity,secret);//token有效期30天
            redisTemplate.opsForValue().set(token, "1",30, TimeUnit.DAYS);
            ResultEntity resultEntity = ResultUtil.success(mUserEntity, "登录成功", token);
            resultEntity.setToken(token);
            return resultEntity;
        }
    }

    @Override
    public ResultEntity loginByEmail(MailEntity mailEntity){
        int code = (int) redisTemplate.opsForValue().get(mailEntity.getEmail());
        if(!mailEntity.getCode().equals(code + "")){
            return ResultUtil.fail(null,"验证码无效");
        }else{
            UserEntity userEntity = userMapper.loginByEmail(mailEntity.getEmail());
            if (userEntity != null) {
                String token = JwtToken.createToken(userEntity,secret);//token有效期30天
                redisTemplate.opsForValue().set(token, "1",30, TimeUnit.DAYS);
                return ResultUtil.success(userEntity, "登录成功", token);
            } else {
                return ResultUtil.fail(null, "登录失败，邮箱不存在", ResultCode.FAIL);
            }
        }
    }
}
