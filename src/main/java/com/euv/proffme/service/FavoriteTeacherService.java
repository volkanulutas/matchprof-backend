package com.euv.proffme.service;

import com.euv.proffme.model.entity.favorite.FavoriteTeacherEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface FavoriteTeacherService {
    FavoriteTeacherEntity findById(Long id) throws EntityNotFoundException;

    List<FavoriteTeacherEntity> findAll();

    FavoriteTeacherEntity save(FavoriteTeacherEntity favoriteTeacher) throws EntityAlreadySavedException;

    FavoriteTeacherEntity update(FavoriteTeacherEntity favoriteTeacher)throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
