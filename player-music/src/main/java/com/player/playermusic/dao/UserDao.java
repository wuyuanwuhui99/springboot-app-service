package com.player.playermusic.dao;

import com.player.playermusic.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<UserEntity, String> {
    @Query(value = "SELECT user_id,create_date,update_date,username,telephone,email,avater,birthday,sex,role from  user WHERE role ='public'  order by rand() LIMIT 1", nativeQuery = true)
    Map<String, Object> findOneUser();

    @Query(value = "select user_id as userId,username,avater  FROM user WHERE user_id =?1", nativeQuery = true)
    List<Map<String, Object>> findByUserId(String userId);

    @Query(value = "select user_id as userId,username,telephone,email,age,sex,avater,role FROM user WHERE user_id =?1 AND password= ?2", nativeQuery = true)
     List<Map<String, Object>> findByUserIdAndPassword(String userId, String password);
}
