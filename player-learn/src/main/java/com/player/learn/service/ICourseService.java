package com.player.learn.service;

import com.player.common.entity.ResultEntity;
import com.player.learn.entity.ChapterLogEntity;
import com.player.learn.entity.CourseLogEntity;

public interface ICourseService {

    ResultEntity findAllByClassify(String classify,int pageNum,int pageSize);

    ResultEntity findAllByCategory(String category);

    ResultEntity findAllByClassifyGroup();

    ResultEntity login(String userId, String password);

    ResultEntity getUserData(String token);

    ResultEntity saveCourseLog(CourseLogEntity courseLogEntity, String token);

}
