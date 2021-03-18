package com.player.music.controller;

import com.player.common.entity.ResultEntity;
import com.player.music.Entity.FavoriteMusicEntity;
import com.player.music.service.IFavoriteMusicService;
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
    @GetMapping("/music-getway/queryFavorite")
    public ResultEntity queryFavorite(@CookieValue(value = "token", required = true) String token, @Param("mid") String mid) {
        return favoriteMusicService.queryFavorite(token, mid);
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
    @PostMapping("/music-getway/addFavorite")
    public ResultEntity addFavorite(@RequestBody FavoriteMusicEntity favoriteMusicEntity, @CookieValue(value = "token", required = true) String token) {
        return favoriteMusicService.addFavorite(favoriteMusicEntity, token);
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
    @DeleteMapping("/music-getway/deleteFavorite")
    public ResultEntity deleteFavorite(@RequestBody FavoriteMusicEntity favoriteMusicEntity, @CookieValue(value = "token", required = true) String token) {
        return favoriteMusicService.deleteFavorite(favoriteMusicEntity,token);
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
    @GetMapping("/music-getway/getFavorite")
    public ResultEntity getFavorite(@CookieValue(value = "token", required = true) String token) {
        return favoriteMusicService.getFavorite(token);
    }
}
