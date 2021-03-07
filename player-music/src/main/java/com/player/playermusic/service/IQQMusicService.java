package com.player.playermusic.service;

public interface IQQMusicService {
    String getDiscList();

    String getLyric(String songmid);

    String getSingerList();

    String getHotKey();

    String search(String keyword);

    String getSingerDetail(String singermid);

    String getRecommend();

    String getSongList(String disstid);

    String getTopList();

    String getMusicList(String topid);

    String getAudioUrl(String mid, String filename);

    String getSingleSong(String mid);
}
