package com.player.playermusic.dao;

import com.player.playermusic.Entity.FavoriteMusicEntity;
import com.player.playermusic.Entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteMusicDao extends JpaRepository<FavoriteMusicEntity, Long> {

    /**
     * @param : mid歌曲mid
     * @author: wuwenqiang
     * @methodsName: findAllByUserIdAndMid
     * @description: 查询是否收藏歌曲
     * @param: userId用户id
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    public List<FavoriteMusicEntity> findAllByUserIdAndMid(String userId, String mid);

    public int deleteAllByMidAndUserId(String userId, String mid);

    public List<FavoriteMusicEntity> findAllByUserId(String userId);
}
