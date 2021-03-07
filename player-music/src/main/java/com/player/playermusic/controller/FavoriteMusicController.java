package com.player.playermusic.controller;

import com.player.playermusic.Entity.FavoriteMusicEntity;
import com.player.playermusic.Entity.ResultEntity;
import com.player.playermusic.service.IFavoriteMusicService;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
@Api(value = "收藏列表", description = "查询是否收藏，添加收藏，取消收藏")
public class FavoriteMusicController {
    @Autowired
    private IFavoriteMusicService favoriteMusicService;

    /**
     * @param : mid歌曲mid
     * @author: wuwenqiang
     * @methodsName: queryFavorite
     * @description: 查询是否收藏歌曲
     * @param: userId用户id
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    @ApiOperation("根据用户命名和mid查询收藏的歌曲 ")
    @GetMapping("/music/queryFavorite")
    public ResultEntity queryFavorite(@CookieValue(value = "userId", required = false) String userId, @Param("mid") String mid) {
        return favoriteMusicService.queryFavorite(userId, mid);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: queryFavorite
     * @description: 添加收藏, 如果是管理员账户，添加到抖音歌曲表
     * @param: favoriteMusicEntity歌曲的json
     * @return: ResultEntity
     * @date: 2020-07-30 23:58
     */
    @ApiOperation("添加收藏,如果是管理员账户，添加到抖音歌曲表")
    @PostMapping("/music/addFavorite")
    public ResultEntity addFavorite(@RequestBody FavoriteMusicEntity favoriteMusicEntity, @CookieValue(value = "userId", required = false) String userId) {
        return favoriteMusicService.addFavorite(favoriteMusicEntity, userId);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: queryFavorite
     * @description: 添加收藏, 如果是管理员账户，添加到抖音歌曲表
     * @param: favoriteMusicEntity歌曲的json
     * @return: ResultEntity
     * @date: 2020-07-30 23:58
     */
    @ApiOperation("取消收藏")
    @DeleteMapping("/music/deleteFavorite")
    public ResultEntity deleteFavorite(@RequestBody FavoriteMusicEntity favoriteMusicEntity) {
        return favoriteMusicService.deleteFavorite(favoriteMusicEntity);
    }

    /**
     * @author: wuwenqiang
     * @methodsName: getFavorite
     * @description: 根据用户id查询该用户收藏的列表
     * @param: favoriteMusicEntity歌曲的json
     * @return: ResultEntity
     * @date: 2020-07-30 23:58
     */
    @ApiOperation("根据用户id查询该用户收藏的列表")
    @GetMapping("/music/getFavorite")
    public ResultEntity getFavorite(@CookieValue(value = "userId", required = false) String userId) {
        return favoriteMusicService.getFavorite(userId);
    }
}
