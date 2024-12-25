package com.euv.proffme.service;

import com.euv.proffme.model.entity.lesson.LessonCategoryEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface LessonCategoryService {
    LessonCategoryEntity findById(Long id) throws EntityNotFoundException;

    List<LessonCategoryEntity> findAll();

    LessonCategoryEntity save(LessonCategoryEntity favoriteTeacher) throws EntityAlreadySavedException;;

    LessonCategoryEntity update(LessonCategoryEntity favoriteTeacher) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
