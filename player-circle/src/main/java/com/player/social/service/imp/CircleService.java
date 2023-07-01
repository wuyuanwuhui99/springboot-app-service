package com.player.social.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.social.entity.CircleEntity;
import com.player.social.entity.CircleLikeEntity;
import com.player.social.entity.LogCircleEntity;
import com.player.social.entity.SayEntity;
import com.player.social.mapper.CircleMapper;
import com.player.social.service.ICircleService;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.Common;
import com.player.common.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
        int permission;
        if(StringUtils.isEmpty(token)){// 如果没有token，权限就是0
            permission = 0;
        }else{// 查询用户信息的权限
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token);
            ResultEntity userResultEntity = restTemplate.exchange(
                    "http://player-user/service/user/getUserData",
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),ResultEntity.class
            ).getBody();
            UserEntity userEntity = JSON.parseObject(JSON.toJSONString(userResultEntity.getData()),UserEntity.class);
            if(userEntity == null){
                permission = 0;
            }else{
                permission = userEntity.getPermission();
            }
        }
        int start = (pageNum - 1) * pageSize;
        List<CircleEntity>circleArticleList = circleMapper.getCircleList(start, pageSize, type, permission);
//        List<String> circleIds = new ArrayList<>();
//        for(CircleEntity circleEntity : circleArticleList){
//            circleIds.add(circleEntity.getRelationId());
//        }
//        List<CircleLikeEntity> circleLikesByCircleIds = circleMapper.getCircleLikesByCircleIds(circleIds);
//        for (CircleLikeEntity circleLikeEntity: circleLikesByCircleIds){
//            for(CircleEntity circleEntity : circleArticleList){// 把点赞的人数放入相对应的说说里面
//                if(circleEntity.getId() == circleLikeEntity.getCircleId()){
//                    circleEntity.getCircleLikes().add(circleLikeEntity);
//                    break;
//                }
//            }
//        }
        Long total = circleMapper.getCircleCount(type,keyword);
        ResultEntity resultEntity = ResultUtil.success(circleArticleList);
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
            String newImgName = Common.generateImage(base64, uploadPath+imgName);
            if(newImgName != null){
                imgs += newImgName + (i == sayEntity.getImgs().length - 1 ? "" : ";");
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
