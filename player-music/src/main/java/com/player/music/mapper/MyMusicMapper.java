package com.player.music.mapper;

import com.player.music.Entity.MyMusiPlayMenuEntity;
import com.player.music.Entity.MyMusicEntity;
import com.player.music.Entity.MySingerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyMusicMapper {
    MyMusicEntity getKeywordMusic();

    List<MyMusicEntity> getMusicClassify();

    List<MyMusicEntity> getMusicListByClassifyId(int classifyId,int start,int pageSize,String userId);

    Long getMusicTotalByClassifyId(int classifyId);

    List<MyMusicEntity> getSingerList(int start,int pageSize);

    Long getSingerTotal();

    List<MyMusiPlayMenuEntity> getMusiPlayMenu(String userId);

    List<MySingerEntity> getMySinger(String userId,int start, int pageSize);

    Long getMySingerCount(String userId);

    List<MyMusicEntity> getMusicRecord(String userId, int start, int pageSize);

    Long getMusicRecordCount(String userId);
}
