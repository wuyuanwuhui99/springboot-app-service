package com.player.playermusic.dao;

import com.player.playermusic.Entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<LogEntity, Long> { }
