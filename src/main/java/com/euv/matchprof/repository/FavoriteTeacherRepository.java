package com.euv.matchprof.repository;

import com.euv.matchprof.model.entity.favorite.FavoriteTeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteTeacherRepository extends JpaRepository<FavoriteTeacherEntity, Long> {
    boolean existsByIdIsNotNull();
}
