package com.euv.matchprof.repository;

import com.euv.matchprof.model.entity.lesson.LessonChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonChargeRepository extends JpaRepository<LessonChargeEntity, Long> {
    boolean existsByIdIsNotNull();
}
