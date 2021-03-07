package com.player.playermusic.service.imp;

import com.player.playermusic.Entity.DouyinEntity;
import com.player.playermusic.Entity.RecordEntity;
import com.player.playermusic.Entity.ResultEntity;
import com.player.playermusic.dao.DouyinDao;
import com.player.playermusic.dao.RecordDao;
import com.player.playermusic.service.IDouyinService;
import com.player.playermusic.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DouyinService implements IDouyinService {
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
    public ResultEntity getDouyinList() {
        List<DouyinEntity> douyinEntityList = douyinDao.findAllByDisabled("0", Sort.by("updateTime").descending());
        return ResultUtil.success(douyinEntityList);
    }
}
