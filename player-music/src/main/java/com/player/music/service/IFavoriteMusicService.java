package com.player.music.service;

import com.player.common.entity.ResultEntity;
import com.player.music.Entity.FavoriteMusicEntity;
import org.springframework.transaction.annotation.Transactional;

public interface IFavoriteMusicService {
    /**
     * @param : mid歌曲mid
     * @author: wuwenqiang
     * @methodsName: queryFavorite
     * @description: 查询addFavorite是否收藏歌曲
     * @param: userId用户id
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    ResultEntity queryFavorite(String userId, String mid);

    /**
     * @param : mid歌曲mid
     * @author: wuwenqiang
     * @methodsName: queryFavorite
     * @description: 添加收藏
     * @param: userId用户id
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    @Transactional
    ResultEntity addFavorite(FavoriteMusicEntity favoriteMusicEntity, String userId);


    /**
     * @author: wuwenqiang
     * @methodsName: deleteFavorite
     * @description: 取消收藏
     * @param: favoriteMusicEntity歌曲的json
     * @return: ResultEntity
     * @date: 2020-07-30 23:58
     */
    ResultEntity deleteFavorite(FavoriteMusicEntity favoriteMusicEntity,String token);

    ResultEntity getFavorite(String userId);
}
