package com.player.music.controller;

import com.player.common.entity.ResultEntity;
import com.player.common.utils.HttpUtils;
import com.player.music.entity.MyMusicFavoriteDirectoryEntity;
import com.player.music.entity.MyMusicFavoriteEntity;
import com.player.music.entity.MyMusicRecordEntity;
import com.player.music.service.IMyMusicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController

@RequestMapping("/service")
public class MyMusicController {
    @Autowired
    private IMyMusicService myMusicService;

    @ApiOperation("获取搜索框默认推荐音乐")
    @GetMapping("/myMusic/getKeywordMusic")
    public ResultEntity getKeywordMusic(HttpServletRequest request,@RequestHeader("Authorization") String token) {
        return myMusicService.getKeywordMusic(HttpUtils.getPath(request));
    }

    @ApiOperation("获取音乐分类")
    @GetMapping("/myMusic/getMusicClassify")
    public ResultEntity getClassifyMusic(HttpServletRequest request) {
        return myMusicService.getMusicClassify(HttpUtils.getPath(request));
    }

    @ApiOperation("获取推荐音乐列表,isRedis表示是否从redis中获取数据")
    @GetMapping("/myMusic/getMusicListByClassifyId")
    public ResultEntity getMusicListByClassifyId(
            HttpServletRequest request,
            @RequestParam(name = "classifyId",required = true) int classifyId,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize,
            @RequestParam(name = "isRedis",defaultValue = "0",required = false) int isRedis,
            @RequestHeader(required = false, value = "Authorization") String token
    ) {
        return myMusicService.getMusicListByClassifyId(HttpUtils.getPath(request), classifyId, pageNum, pageSize, isRedis != 0, token);
    }

    @ApiOperation("获取歌手")
    @GetMapping("/myMusic/getMusicAuthorListByCategoryId")
    public ResultEntity getMusicAuthorListByCategoryId(
            HttpServletRequest request,
            @RequestHeader(value = "Authorization",required = false) String token,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize,
            @RequestParam(name = "categoryId",required = true) int categoryId
    ) {
        return myMusicService.getMusicAuthorListByCategoryId(HttpUtils.getPath(request),token, categoryId, pageNum, pageSize);
    }

    @ApiOperation("获取歌手专辑")
    @GetMapping("/myMusic/getMusicListByAuthorId")
    public ResultEntity getMusicListByAuthorId(
            HttpServletRequest request,
            @RequestHeader(value = "Authorization",required = false) String token,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize,
            @RequestParam(name = "authorId",required = true) int authorId
    ) {
        return myMusicService.getMusicListByAuthorId(HttpUtils.getPath(request),token, authorId, pageNum, pageSize);
    }

    @ApiOperation("获取我关注的歌手")
    @GetMapping("/myMusic-getway/getFavoriteAuthor")
    public ResultEntity getFavoriteAuthor(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize
    ) {
        return myMusicService.getFavoriteAuthor(token,pageNum,pageSize);
    }

    @ApiOperation("获取我关注的歌手")
    @PostMapping("/myMusic-getway/insertFavoriteAuthor/{authorId}")
    public ResultEntity insertFavoriteAuthor(
            @RequestHeader("Authorization") String token,
            @PathVariable(name = "authorId",required = true) int authorId
    ) {
        return myMusicService.insertFavoriteAuthor(token,authorId);
    }

    @ApiOperation("获取我关注的歌手")
    @DeleteMapping("/myMusic-getway/deleteFavoriteAuthor/{authorId}")
    public ResultEntity deleteFavoriteAuthor(
            @RequestHeader("Authorization") String token,
            @PathVariable(name = "authorId",required = true) int authorId
    ) {
        return myMusicService.deleteFavoriteAuthor(token,authorId);
    }

    @ApiOperation("获取最近播放的歌曲")
    @GetMapping("/myMusic-getway/getMusicRecord")
    public ResultEntity getMusicRecord(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize
    ) {
        return myMusicService.getMusicRecord(token,pageNum,pageSize);
    }

    @ApiOperation("插入播放记录")
    @PostMapping("/myMusic-getway/insertMusicRecord")
    public ResultEntity insertMusicRecord(
            @RequestHeader("Authorization") String token,
            @RequestBody MyMusicRecordEntity myMusicRecordEntity
    ) {
        return myMusicService.insertMusicRecord(token,myMusicRecordEntity);
    }

    @ApiOperation("插入音乐收藏")
    @PostMapping("/myMusic-getway/insertMusicLike/{id}")
    public ResultEntity insertMusicLike(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") int id
    ) {
        return myMusicService.insertMusicLike(token,id);
    }

    @ApiOperation("删除音乐收藏")
    @DeleteMapping("/myMusic-getway/deleteMusicLike/{id}")
    public ResultEntity deleteMusicLike(
            @RequestHeader("Authorization") String token,
            @PathVariable("id") int id
    ) {
        return myMusicService.deleteMusicLike(token,id);
    }

    @ApiOperation("查询音乐收藏")
    @GetMapping("/myMusic-getway/getMusicLike")
    public ResultEntity getMusicLike(
            @RequestHeader("Authorization") String token,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize
    ) {
        return myMusicService.getMusicLike(token,pageNum,pageSize);
    }

    @ApiOperation("查询音乐收藏")
    @GetMapping("/myMusic/searchMusic")
    public ResultEntity searchMusic(
            @RequestHeader(name = "Authorization",required = false) String token,
            @RequestParam(name = "keyword",required = true) String keyword,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize
    ) {
        return myMusicService.searchMusic(token,keyword,pageNum,pageSize);
    }

    @ApiOperation("获取歌手分类")
    @GetMapping("/myMusic/getMusicAuthorCategory")
    public ResultEntity getMusicAuthorCategory(
            HttpServletRequest request
    ) {
        return myMusicService.getMusicAuthorCategory(HttpUtils.getPath(request));
    }

    @ApiOperation("查询音乐收藏")
    @GetMapping("/myMusic-getway/insertMusicRecord")
    public ResultEntity insertMusicRecord(
            HttpServletRequest request
    ) {
        return myMusicService.getMusicAuthorCategory(HttpUtils.getPath(request));
    }

    @ApiOperation("查询收藏夹列表")
    @GetMapping("/myMusic-getway/getFavoriteDirectory")
    public ResultEntity getFavoriteDirectory(
            @RequestParam(name = "musicId",required = true) Long musicId,
            @RequestHeader(name = "Authorization",required = true) String token
    ) {
        return myMusicService.getFavoriteDirectory(token,musicId);
    }

    @ApiOperation("创建收藏夹")
    @PostMapping("/myMusic-getway/insertFavoriteDirectory")
    public ResultEntity insertFavoriteDirectory(
            @RequestBody MyMusicFavoriteDirectoryEntity favoriteDirectoryEntity,
            @RequestHeader(name = "Authorization",required = false) String token
    ) {
        return myMusicService.insertFavoriteDirectory(token,favoriteDirectoryEntity);
    }

    @ApiOperation("删除收藏夹")
    @DeleteMapping("/myMusic-getway/deleteFavoriteDirectory/{favoriteId}")
    public ResultEntity deleteFavoriteDirectory(
            @PathVariable("favoriteId") Long favoriteId,
            @RequestHeader(name = "Authorization",required = false) String token
    ) {
        return myMusicService.deleteFavoriteDirectory(token,favoriteId);
    }

    @ApiOperation("查询收藏夹音乐")
    @GetMapping("/myMusic-getway/getMusicListByFavoriteId")
    public ResultEntity getMusicListByFavoriteId(
            @RequestParam(name = "favoriteId",required = true) Long favoriteId,
            @RequestParam(name = "pageNum",required = true) int pageNum,
            @RequestParam(name = "pageSize",required = true) int pageSize,
            @RequestHeader(name = "Authorization",required = true) String token
    ) {
        return myMusicService.getMusicListByFavoriteId(token,favoriteId,pageNum,pageSize);
    }

    @ApiOperation("更新收藏夹名称")
    @PutMapping("/myMusic-getway/updateFavoriteDirectory")
    public ResultEntity updateFavoriteDirectory(
            @RequestBody MyMusicFavoriteDirectoryEntity favoriteDirectoryEntity,
            @RequestHeader(name = "Authorization",required = false) String token
    ) {
        return myMusicService.updateFavoriteDirectory(token,favoriteDirectoryEntity.getId(),favoriteDirectoryEntity.getName());
    }

    @ApiOperation("查询音乐收藏")
    @GetMapping("/myMusic-getway/isMusicFavorite/{musicId}")
    public ResultEntity isMusicFavorite(
            @PathVariable("musicId") Long musicId,
            @RequestHeader(name = "Authorization",required = false) String token
    ) {
        return myMusicService.isMusicFavorite(token,musicId);
    }

    @ApiOperation("添加音乐收藏夹")
    @PostMapping("/myMusic-getway/insertMusicFavorite/{musicId}")
    public ResultEntity insertMusicFavorite(
            @PathVariable("musicId") Long musicId,
            @RequestBody List<MyMusicFavoriteEntity> musicFavoriteEntityList,
            @RequestHeader(name = "Authorization",required = false) String token
    ) {
        return myMusicService.insertMusicFavorite(token,musicId,musicFavoriteEntityList);
    }
}
