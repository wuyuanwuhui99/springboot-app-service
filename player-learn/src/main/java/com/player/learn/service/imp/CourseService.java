package com.player.learn.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.entity.UserEntity;
import com.player.common.utils.JwtToken;
import com.player.learn.dao.CourseDao;
import com.player.learn.dao.CourseLogDao;
import com.player.learn.entity.CourseEntity;
import com.player.learn.entity.CourseLogEntity;
import com.player.learn.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CourseService implements ICourseService {

    JwtToken jwtToken = new JwtToken();

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseLogDao courseLogDao;

    /**
     * @author: wuwenqiang
     * @methodsName: findAllByClassifyGroup
     * @description: 查询所有大分类
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @Override
    public ResultEntity findAllByClassify(String classify,int pageNum,int pageSize) {
        String path = "/service/learn/findAllByClassify?classify="+classify+"&pageNum="+pageNum+"&pageSize="+pageSize;
        String result = (String) redisTemplate.opsForValue().get(path);
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            PageRequest pageRequest = new PageRequest(pageNum, pageSize, Sort.by("updateTime").descending());
            Page<CourseEntity> allByClassify = courseDao.findAllByClassify(classify, pageRequest);
            Long total = allByClassify.getTotalElements();
            List<CourseEntity> courseEntities = allByClassify.getContent();
            ResultEntity resultEntity = ResultUtil.success(courseEntities,total);
            redisTemplate.opsForValue().set(path, JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
            return resultEntity;
        }
    }

    /**
     * @param :category类别
     * @author: wuwenqiang
     * @methodsName: findAllByCategory
     * @description: 根据类别查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @Override
    public ResultEntity findAllByCategory(String category) {
        String result = (String) redisTemplate.opsForValue().get("/service/learn/findAllByCategory?category="+category);
        if(result != null && !"".equals(result)){
            List<CourseEntity> list = JSON.parseArray(result,CourseEntity.class);
            return ResultUtil.success(list);
        }else{
            List<CourseEntity> list = courseDao.findAllByCategory(category, Sort.by("createTime").descending());
            redisTemplate.opsForValue().set("/service/learn/findAllByCategory?category="+category, JSON.toJSONString(list),1, TimeUnit.DAYS);
            return ResultUtil.success(list);
        }
    }

    /**
     * @author: wuwenqiang
     * @methodsName: findAllByClassifyGroup
     * @description: 查询所有大分类
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @Override
    public ResultEntity findAllByClassifyGroup() {
        String result = (String) redisTemplate.opsForValue().get("/service/learn/findAllByClassifyGroup");
        if(result != null && !"".equals(result)){
            List<Map> list = JSON.parseArray(result,Map.class);
            return ResultUtil.success(list);
        }else{
            List<Map<String,String>> list = courseDao.findAllByClassifyGroup();
            redisTemplate.opsForValue().set("/service/learn/findAllByClassifyGroup", JSON.toJSONString(list),1, TimeUnit.DAYS);
            return ResultUtil.success(list);
        }
    }


    /**
     * @param :courseLogEntity课程实体类
     * @param :token用户token
     * @author: wuwenqiang
     * @methodsName: saveCourseLog
     * @description: 保存日志接口
     * @return: ResultEntity
     * @date: 2021-01-09 11:36
     */
    @Override
    public ResultEntity saveCourseLog(CourseLogEntity courseLogEntity,String token) {
        UserEntity userEntity = jwtToken.parserToken(token, UserEntity.class);
        if(!ObjectUtils.isEmpty(userEntity)){
            courseLogEntity.setUserId(userEntity.getUserId());
        }
        return ResultUtil.success(courseLogDao.save(courseLogEntity));
    }
}
