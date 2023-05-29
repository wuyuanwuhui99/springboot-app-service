package com.player.music.mapper;

import com.player.music.Entity.MyMusicEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyMusicMapper {
    MyMusicEntity getKeywordMusic();

    List<MyMusicEntity> getMusicClassify();

    List<MyMusicEntity> getMusicByClassifyName(String classifyName,int start,int pageSize);
}
