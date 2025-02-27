package com.player.music.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.utils.JwtToken;
import com.player.music.entity.FavoriteMusicEntity;
import com.player.music.service.IFavoriteMusicService;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ResultEntity queryFavorite(HttpServletRequest request, @Param("mid") String mid) {
        String userId = JwtToken.getId(request.getHeader("Authorization"));
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
    @PostMapping("/music-getway/addFavorite")
    public ResultEntity addFavorite(@RequestBody FavoriteMusicEntity favoriteMusicEntity,HttpServletRequest request) {
        String userId = JwtToken.getId(request.getHeader("Authorization"));
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
    @DeleteMapping("/music-getway/deleteFavorite")
    public ResultEntity deleteFavorite(@RequestBody FavoriteMusicEntity favoriteMusicEntity,HttpServletRequest request) {
        String userId = JwtToken.getId(request.getHeader("Authorization"));
        return favoriteMusicService.deleteFavorite(favoriteMusicEntity,userId);
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
    public ResultEntity getFavorite(HttpServletRequest request) {
        String userId = JwtToken.getId(request.getHeader("Authorization"));
        return favoriteMusicService.getFavorite(userId);
    }
}
