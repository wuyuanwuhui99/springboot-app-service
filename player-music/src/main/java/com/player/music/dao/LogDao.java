package com.player.music.dao;

import com.player.music.Entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogDao extends JpaRepository<LogEntity, Long> { }
