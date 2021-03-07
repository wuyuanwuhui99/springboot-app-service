package com.player.ebook.mapper;

import com.player.common.entity.LogEntity;
import com.player.ebook.entity.BannerEntity;
import com.player.ebook.entity.BookEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    Long log(LogEntity logEntity);

    List<BookEntity> findBookList(
            @Param("pageSize") Integer pageSize,
            @Param("pageNum") Integer pageNum,
            @Param("classify") String classify,
            @Param("category") String category,
            @Param("keyword") String keyword
    );

    Long findBookListTotal(
            @Param("classify")String classify,
            @Param("category")String category,
            @Param("keyword")String keyword
    );

    List<Map<String,String>>findAllByClassifyGroup();

    List<BannerEntity> getBanner();
}
