package com.player.music.dao;

import com.player.music.entity.DouyinEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DouyinDao extends JpaRepository<DouyinEntity, Long> {
    /**
     * @param :disabled是否禁用
     * @param :sort排序规则
     * @author: wuwenqiang
     * @methodsName: findAllByDisabled
     * @description: 查询抖音播放列表
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    List<DouyinEntity> findAllByDisabled(String disabled, Sort sort);

    @Modifying
    @Transactional
    @Query("UPDATE DouyinEntity SET lyric=?1 WHERE mid=?2 AND lyric IS NULL")
    int updateLyric(String lyric, String mid);
}
