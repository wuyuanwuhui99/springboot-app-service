package com.player.playermusic.dao;

import com.player.playermusic.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserDao extends JpaRepository<UserEntity, String> {
    @Query(value = "select user_id as userId,username,avater  FROM user WHERE role = ?1", nativeQuery = true)
    public List<Map<String, Object>> findAllByRole(String role);

    @Query(value = "select user_id as userId,username,avater  FROM user WHERE user_id =?1", nativeQuery = true)
    public List<Map<String, Object>> findByUserId(String userId);

    @Query(value = "select user_id as userId,username,telephone,email,age,sex,avater,role FROM user WHERE user_id =?1 AND password= ?2", nativeQuery = true)
    public List<Map<String, Object>> findByUserIdAndPassword(String userId, String password);
}
