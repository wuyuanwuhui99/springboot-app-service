package com.player.learn.dao;

import com.player.learn.entity.ChapterEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterDao  extends JpaRepository<ChapterEntity, Long> {
    /**
     * @param :courseName课程名称
     * @param :sort排序规则
     * @author: wuwenqiang
     * @methodsName: findAllByCourseName
     * @description: 根据大分类查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    public List<ChapterEntity> findAllByCourseName(String courseName, Sort sort);
}
