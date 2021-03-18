package com.player.music.service.imp;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.music.Entity.RecordEntity;
import com.player.music.dao.RecordDao;
import com.player.music.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordService implements IRecordService {
    @Autowired
    private RecordDao recordDao;

    /**
     * @author: wuwenqiang
     * @methodsName: record
     * @description: 播放记录
     * @param: recordEntity 歌曲的实体对象
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    @Override
    @Transactional
    public ResultEntity record(RecordEntity recordEntity) {
        recordDao.save(recordEntity);
        int time = recordDao.updateTime(recordEntity.getId());
        return ResultUtil.success(time);
    }
}
