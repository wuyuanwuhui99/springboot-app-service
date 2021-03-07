package com.player.learn.service.imp;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.learn.dao.ChapterDao;
import com.player.learn.dao.ChapterLogDao;
import com.player.learn.entity.ChapterLogEntity;
import com.player.learn.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ChapterService implements IChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Autowired
    private ChapterLogDao chapterLogDao;

    @Override
    public ResultEntity findAllByCourseName(String courseName) {
        return ResultUtil.success(chapterDao.findAllByCourseName(courseName,  Sort.by("createTime").descending()));
    }

    @Override
    public ResultEntity saveChapterLog(ChapterLogEntity chapterLogEntity, String token) {
        return  ResultUtil.success(chapterLogDao.save(chapterLogEntity));
    }
}
