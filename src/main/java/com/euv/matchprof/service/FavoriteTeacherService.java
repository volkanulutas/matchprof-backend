package com.euv.matchprof.service;

import com.euv.matchprof.model.entity.favorite.FavoriteTeacherEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;

import java.util.List;

public interface FavoriteTeacherService {
    FavoriteTeacherEntity findById(Long id) throws EntityNotFoundException;

    List<FavoriteTeacherEntity> findAll();

    FavoriteTeacherEntity save(FavoriteTeacherEntity favoriteTeacher) throws EntityAlreadySavedException;

    FavoriteTeacherEntity update(FavoriteTeacherEntity favoriteTeacher)throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    boolean hasAnyRecord();
}
