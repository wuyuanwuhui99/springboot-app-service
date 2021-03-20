package com.player.music.controller;

import com.player.common.entity.ResultEntity;
import com.player.music.service.IQQMusicService;
import feign.Param;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/service")
public class QQMusicController {
    @Autowired
    private IQQMusicService qqMusicService;

    @ApiOperation("获取推荐音乐数据,请求地地址")
    @GetMapping("/music/getDiscList")
    public ResultEntity getDiscList() {
        return qqMusicService.getDiscList();
    }

    @ApiOperation("根据歌曲mid查询歌词")
    @GetMapping("/music/getLyric")
    public ResultEntity getLyric(@Param("songmid") String songmid) {
        return qqMusicService.getLyric(songmid);
    }

    @ApiOperation("获取歌手列表")
    @GetMapping("/music/getSingerList")
    public ResultEntity getSingerList() {
        return qqMusicService.getSingerList();
    }

    @ApiOperation("获取热门推荐")
    @GetMapping("/music/getHotKey")
    public ResultEntity getHotKey() {
        return qqMusicService.getHotKey();
    }

    @ApiOperation("搜索歌曲")
    @GetMapping("/music/search")
    public ResultEntity search(
            @Param("catZhida") String catZhida,
            @Param("p") String p,
            @Param("n") String n,
            @Param("w") String w
    ) {
        return qqMusicService.search(catZhida,p,n,w);
    }

    @ApiOperation("获取歌手的歌曲")
    @GetMapping("/music/getSingerDetail")
    public ResultEntity getSingerDetail(@Param("singermid") String singermid) {
        return qqMusicService.getSingerDetail(singermid);
    }

    @ApiOperation("获取歌手的歌曲")
    @GetMapping("/music/getRecommend")
    public ResultEntity getRecommend() {
        return qqMusicService.getRecommend();
    }

    @ApiOperation("获取歌单数据")
    @GetMapping("/music/getSongList")
    public ResultEntity getSongList(@Param("disstid") String disstid) {
        return qqMusicService.getSongList(disstid);
    }

    @ApiOperation("获取歌单数据")
    @GetMapping("/music/getTopList")
    public ResultEntity getTopList() {
        return qqMusicService.getTopList();
    }

    @ApiOperation("获取音乐列表")
    @GetMapping("/music/getMusicList")
    public ResultEntity getMusicList(@Param("topid") String topid) {
        return qqMusicService.getMusicList(topid);
    }

    @ApiOperation("获取歌曲的url")
    @GetMapping("/music/getAudioUrl")
    public ResultEntity getAudioUrl(@Param("mid") String mid, @Param("filename") String filename) {
        return qqMusicService.getAudioUrl(mid, filename);
    }

    @ApiOperation("获取歌曲的key")
    @GetMapping("/music/getSingleSong")
    public ResultEntity getSingleSong(@Param("songmid") String songmid) {
        return qqMusicService.getSingleSong(songmid);
    }
}
