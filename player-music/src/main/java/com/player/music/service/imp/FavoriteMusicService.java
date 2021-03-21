package com.player.music.service.imp;

import com.player.common.entity.ResultEntity;
import com.player.common.entity.ResultUtil;
import com.player.common.utils.HttpUtils;
import com.player.common.utils.JwtToken;
import com.player.music.Entity.DouyinEntity;
import com.player.music.Entity.FavoriteMusicEntity;
import com.player.music.Entity.UserEntity;
import com.player.music.dao.DouyinDao;
import com.player.music.dao.FavoriteMusicDao;
import com.player.music.dao.UserDao;
import com.player.music.service.IFavoriteMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteMusicService implements IFavoriteMusicService {
    @Autowired
    private FavoriteMusicDao favoriteMusicDao;

    @Autowired
    private DouyinDao douyinDao;

    @Autowired
    private UserDao userDao;

    /**
     * @param : mid歌曲mid
     * @author: wuwenqiang
     * @methodsName: queryFavorite
     * @description: 查询是否收藏歌曲
     * @param: userId用户id
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    @Override
    public ResultEntity queryFavorite(String userId, String mid) {
        List<FavoriteMusicEntity> favoriteMusicEntities = favoriteMusicDao.findAllByUserIdAndMid(userId, mid);
        return ResultUtil.success(favoriteMusicEntities.size());
    }

    /**
     * @param : mid歌曲mid
     * @author: wuwenqiang
     * @methodsName: addFavorite
     * @description: 添加收藏，如果是管理员用户，添加到抖音歌曲表
     * @param: userId用户id
     * @return: ResultEntity
     * @date: 2020-07-25 8:26
     */
    @Transactional
    @Override
    public ResultEntity addFavorite(FavoriteMusicEntity favoriteMusicEntity, String userId) {
        List<FavoriteMusicEntity> favoriteMusicEntities = favoriteMusicDao.findAllByUserIdAndMid(userId, favoriteMusicEntity.getMid());
        if (favoriteMusicEntities.size() != 0) {
            return ResultUtil.fail("已经收藏过，请勿重复收藏");
        }
        FavoriteMusicEntity musicEntity = favoriteMusicDao.save(favoriteMusicEntity);

        Optional<UserEntity> userEntityOptional = userDao.findById(userId);

        if (userEntityOptional.isPresent()) {//查询用户是否是管理员，如果是管理员才能插入
            UserEntity userEntity = userEntityOptional.get();

            if (userEntity.getRole() == "admin") {
                Optional<DouyinEntity> douyinEntityOptional = douyinDao.findById(favoriteMusicEntity.getId());
                if (douyinEntityOptional.isPresent() == false) {//如果抖音歌曲表里面没有这首歌，才能插入
                    DouyinEntity douyinEntity = new DouyinEntity();
                    String audioName = null;
                    String picName = null;
                    if (!StringUtils.isEmpty(favoriteMusicEntity.getUrl())) {//如果有url地址，下载歌曲
                        audioName = HttpUtils.doGetFile(favoriteMusicEntity.getUrl(), "E:\\Node\\music\\public\\audio\\");
                        douyinEntity.setLocalUrl("/audio/" + audioName);
                    } else {//如果没有歌曲地址，设置默认的歌曲地址
                        douyinEntity.setLocalUrl("/audio/" + favoriteMusicEntity.getName() + ".m4a");
                    }
                    if (!StringUtils.isEmpty(favoriteMusicEntity.getImage())) {//如果有图片地址，下载图片
                        picName = HttpUtils.doGetFile(favoriteMusicEntity.getImage(), "E:\\Node\\music\\public\\images\\song\\");
                        douyinEntity.setLocalImage("/images/song/" + audioName);
                    } else {//如果没有图片，设置默认的图片的地址
                        douyinEntity.setLocalImage("/images/song/" + favoriteMusicEntity.getName() + ".jpg");
                    }
                    douyinEntity.setPlayMode("local");
                    douyinEntity.setLocalUrl(picName);
                    douyinEntity.setId(favoriteMusicEntity.getId());
                    douyinEntity.setAlbummid(favoriteMusicEntity.getAlbummid());
                    douyinEntity.setDuration(favoriteMusicEntity.getDuration());
                    douyinEntity.setImage(favoriteMusicEntity.getImage());
                    douyinEntity.setMid(favoriteMusicEntity.getMid());
                    douyinEntity.setSinger(favoriteMusicEntity.getSinger());
                    douyinEntity.setUrl(favoriteMusicEntity.getUrl());
                    douyinEntity.setCreateTime(favoriteMusicEntity.getCreateTime());
                    douyinEntity.setTimer(0);
                    douyinEntity.setUpdateTime(favoriteMusicEntity.getUpdateTime());
                    douyinEntity.setKugouUrl(favoriteMusicEntity.getKugouUrl());
                    douyinEntity.setPlayMode(favoriteMusicEntity.getPlayMode());
                    douyinEntity.setOtherUrl(favoriteMusicEntity.getOtherUrl());
                    douyinEntity.setLocalUrl(favoriteMusicEntity.getLocalUrl());
                    douyinEntity.setDisabled("0");
                    douyinEntity.setLyric(favoriteMusicEntity.getLyric());
                    douyinEntity.setLocalImage(favoriteMusicEntity.getLocalImage());
                    douyinDao.save(douyinEntity);
                }
            }
        }
        if (musicEntity != null) {
            return ResultUtil.success("收藏成功");
        } else {
            return ResultUtil.fail("收藏失败");
        }
        

    }

    /**
     * @author: wuwenqiang
     * @methodsName: deleteFavorite
     * @description: 取消收藏
     * @param: favoriteMusicEntity歌曲的json
     * @return: ResultEntity
     * @date: 2020-07-30 23:58
     */
    @Override
    public ResultEntity deleteFavorite(FavoriteMusicEntity favoriteMusicEntity,String userId) {
        favoriteMusicDao.deleteAllByMidAndUserId(favoriteMusicEntity.getMid(), userId);
        return ResultUtil.success("删除成功");
    }

    @Override
    public ResultEntity getFavorite(String userId) {
        return ResultUtil.success(favoriteMusicDao.findAllByUserId(userId));
    }
}
