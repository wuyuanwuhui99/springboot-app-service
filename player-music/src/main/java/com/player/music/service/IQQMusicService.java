package com.player.music.service;

import com.player.common.entity.ResultEntity;

public interface IQQMusicService {
    ResultEntity getDiscList();

    ResultEntity getLyric(String songmid);

    ResultEntity getSingerList();

    ResultEntity getHotKey();

    ResultEntity search(String catZhida,String p,String n, String w);

    ResultEntity getSingerDetail(String singermid);

    ResultEntity getRecommend();

    ResultEntity getSongList(String disstid);

    ResultEntity getTopList();

    ResultEntity getMusicList(String topid);

    ResultEntity getAudioUrl(String mid, String filename);

    ResultEntity getSingleSong(String mid);
}
