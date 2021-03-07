package com.player.ebook.service;

import com.player.common.entity.ResultEntity;

public interface IBookService {
    ResultEntity findBookList(
            Integer pageSize,
            Integer pageNum,
            String classify,
            String category,
            String keyword,
            String path
    );

    ResultEntity getUserData(String token);

    ResultEntity findAllByClassifyGroup(String token);

    ResultEntity getBanner(String token);
}
