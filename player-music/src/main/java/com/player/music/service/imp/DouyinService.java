package com.player.music.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.music.entity.DouyinEntity;
import com.player.music.dao.DouyinDao;
import com.player.music.service.IDouyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DouyinService implements IDouyinService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DouyinDao douyinDao;

    /**
     * @author: wuwenqiang
     * @methodsName: getDouyinList
     * @description: 查询抖音播放列表
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    @Override
    public ResultEntity getDouyinList(String path) {
        String result = (String) redisTemplate.opsForValue().get(path);
        ResultEntity resultEntity;
        if(StringUtils.isEmpty(result)){
            List<DouyinEntity> douyinEntityList = douyinDao.findAllByDisabled("0", Sort.by("updateTime").descending());
            resultEntity = ResultUtil.success(douyinEntityList);
            redisTemplate.opsForValue().set(path,JSON.toJSONString(resultEntity),1, TimeUnit.DAYS);
        }else{
            resultEntity= JSON.parseObject(result,ResultEntity.class);
        }
        return resultEntity;
    }

}
