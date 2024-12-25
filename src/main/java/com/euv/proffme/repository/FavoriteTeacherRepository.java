package com.euv.proffme.repository;

import com.euv.proffme.model.entity.favorite.FavoriteTeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteTeacherRepository extends JpaRepository<FavoriteTeacherEntity, Long> {}
