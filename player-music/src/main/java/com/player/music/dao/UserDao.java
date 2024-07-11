package com.player.music.dao;

import com.player.music.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<UserEntity, String> {
    @Query(value = "SELECT user_id,create_date,update_date,username,telephone,email,avater,birthday,sex,role from  user WHERE role ='public'  order by rand() LIMIT 1", nativeQuery = true)
    Map<String, Object> findOneUser();

    @Query(value = "select user_id as userId,username,telephone,email,age,sex,avater,role FROM user WHERE user_id =?1", nativeQuery = true)
    List<UserEntity> findByUserId(String userId);

    @Query(value = "select user_id as userId,username,telephone,email,age,sex,avater,role FROM user WHERE user_id =?1 AND password= ?2", nativeQuery = true)
     List<UserEntity> findByUserIdAndPassword(String userId, String password);

    @Transactional
    @Modifying
    @Query(value = " UPDATE user SET avater = ?1, update_date = ?2 WHERE user_id = ?3", nativeQuery = true)
    int updateAvater(String avater,String date, String userId);
}
