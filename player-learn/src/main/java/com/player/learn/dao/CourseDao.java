package com.player.learn.dao;

import com.player.learn.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseDao extends JpaRepository<CourseEntity, Long>{

    /**
     * @param :大分类
     * @param :sort排序规则
     * @param pageRequest
     * @author: wuwenqiang
     * @methodsName: findAllByClassify
     * @description: 根据大分类查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    Page<CourseEntity> findAllByClassify(String classify, Pageable pageable);

    /**
     * @param :category类别
     * @param :sort排序规则
     * @author: wuwenqiang
     * @methodsName: findAllByCategory
     * @description: 根据类别查询课程
     * @return: List
     * @date: 2021-01-07 22:39
     */
    List<CourseEntity> findAllByCategory(String category, Sort sort);

    /**
     * @author: wuwenqiang
     * @methodsName: findAllByClassifyGroup
     * @description: 查询分组
     * @return: List
     * @date: 2021-01-07 22:39
     */
    @Query(value = "SELECT '推荐' as classify FROM dual UNION ALL"+
            "(SELECT classify FROM course WHERE classify not in ('热门课程','最新课程','轮播') " +
            "GROUP BY classify ORDER BY update_time DESC)",nativeQuery = true)
    List<Map<String,String>> findAllByClassifyGroup();
}
