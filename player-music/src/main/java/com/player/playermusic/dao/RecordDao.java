package com.player.playermusic.dao;

import com.player.playermusic.Entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RecordDao extends JpaRepository<RecordEntity, Long> {
    /**
     * @author: wuwenqiang
     * @methodsName: updateTime
     * @description: 抖音列表播放记录+1
     * @param: id 歌曲id
     * @return: int
     * @date: 2020-07-25 8:26
     */
    @Modifying
    @Query(value = "UPDATE DouyinEntity SET timer = timer+1 WHERE id = ?1")
    int updateTime(Long id);
}
