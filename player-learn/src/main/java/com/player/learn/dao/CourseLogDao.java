package com.player.learn.dao;

import com.player.learn.entity.CourseLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLogDao extends JpaRepository<CourseLogEntity, Long> {

}
