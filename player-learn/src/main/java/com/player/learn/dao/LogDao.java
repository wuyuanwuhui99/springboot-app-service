package com.player.learn.dao;

import com.player.learn.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<LogEntity,Long> {
}
