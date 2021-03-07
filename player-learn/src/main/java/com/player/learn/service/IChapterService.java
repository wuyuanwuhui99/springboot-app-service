package com.player.learn.service;

import com.player.common.entity.ResultEntity;
import com.player.learn.entity.ChapterLogEntity;

public interface IChapterService {

    ResultEntity findAllByCourseName(String courseName);

    ResultEntity saveChapterLog(ChapterLogEntity chapterLogEntity, String token);
}
