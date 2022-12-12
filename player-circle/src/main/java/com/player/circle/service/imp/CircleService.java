package com.player.circle.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.circle.entity.CircleEntity;
import com.player.circle.entity.LogCircleEntity;
import com.player.circle.entity.SayEntity;
import com.player.circle.mapper.CircleMapper;
import com.player.circle.service.ICircleService;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.player.common.utils.HttpUtils.getRequestData;

@Service
public class CircleService implements ICircleService {
    @Value("${static.upload-path}")
    private String uploadPath;

    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author: wuwenqiang
     * @description: 获取电影圈列表,插入日志表
     * @date: 2022-11-17 23:15
     */
    @Override
    public ResultEntity getCircleArticleList(int pageNum, int pageSize, String type,String keyword,String token) {
        int start = (pageNum - 1) * pageSize;
        List<CircleEntity>circleEntities = circleMapper.getCircleArticleList(start, pageSize, type);
        Long total = circleMapper.getCircleCount("movie",keyword);
        List<LogCircleEntity>logCircleEntities = new ArrayList<>();
        String userId = JwtToken.getUserId(token);
        for(CircleEntity circleEntity:circleEntities){
            LogCircleEntity logCircleEntity = new LogCircleEntity();
            logCircleEntity.setCircleId(circleEntity.getId());
            logCircleEntity.setUserId(userId);
            logCircleEntities.add(logCircleEntity);
        }
        circleMapper.insertLog(logCircleEntities);
        ResultEntity resultEntity = ResultUtil.success(circleMapper.getCircleArticleList(start, pageSize, type));
        resultEntity.setTotal(total);
        return resultEntity;
    }

    /**
     * @author: wuwenqiang
     * @description: 获取用户信息
     * @date: 2022-11-17 23:14
     */
    @Override
    public ResultEntity getUserData(String token) {
        return getRequestData(restTemplate, "http://player-user/service/user/getUserData", token, HttpMethod.GET, null);
    }

    /**
     * @author: wuwenqiang
     * @description: 获取每条电影圈评论数量和浏览数量和点赞数量
     * @date: 2022-12-03 16:01
     */
    @Override
    public ResultEntity getCircleArticleCount(int id) {
        return ResultUtil.success(circleMapper.getCircleArticleCount(id));
    }

    /**
     * @author: wuwenqiang
     * @description: 获取热门影评
     * @date: 2022-12-03 16:02
     */
    @Override
    public ResultEntity getHotCommentMovie(String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(circleMapper.getHotCommentMovie());
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取最近更新的电影
     * @date: 2022-12-03 16:02
     */
    @Override
    public ResultEntity getLastModifyMovie(String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        if(!StringUtils.isEmpty(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            ResultEntity resultEntity = ResultUtil.success(circleMapper.getLastModifyMovie());
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @author: wuwenqiang
     * @description: 获取最近更新的电影
     * @date: 2022-12-03 16:02
     */
    @Override
    public ResultEntity saveSay(SayEntity sayEntity, String token){
        String imgs = "";
        for(int i = 0; i < sayEntity.getImgs().length; i++){
            String base64 = sayEntity.getImgs()[i];
            String ext = base64.replaceAll(";base64,.+","").replaceAll("data:image/","");
            base64 = base64.replaceAll("data:image/.+base64,","");
            String imgName = UUID.randomUUID().toString().replace("-", "") + "." + ext;
            Boolean result = Common.generateImage(base64, uploadPath+imgName);
            if(result){
                imgs += imgName+ (i == sayEntity.getImgs().length - 1 ? "" : ";");
            }
        }
        CircleEntity circleEntity = new CircleEntity();
        circleEntity.setContent(sayEntity.getContent());
        circleEntity.setImgs(imgs);
        UserEntity userEntity = JwtToken.parserToken(token, UserEntity.class);
        circleEntity.setUserId(userEntity.getUserId());
        circleEntity.setType("movie");
        return ResultUtil.success(circleMapper.saveSay(circleEntity));
    }
}
