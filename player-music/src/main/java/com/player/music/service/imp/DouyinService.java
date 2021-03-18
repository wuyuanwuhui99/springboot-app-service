package com.player.music.service.imp;

import com.alibaba.fastjson.JSON;
import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.music.Entity.DouyinEntity;
import com.player.music.dao.DouyinDao;
import com.player.music.service.IDouyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(result != null && !"".equals(result)){
            ResultEntity resultEntity= JSON.parseObject(result,ResultEntity.class);
            return resultEntity;
        }else{
            List<DouyinEntity> douyinEntityList = douyinDao.findAllByDisabled("0", Sort.by("updateTime").descending());
            return ResultUtil.success(douyinEntityList);
        }
    }

}
