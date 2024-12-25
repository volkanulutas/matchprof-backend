package com.euv.proffme.service;

import com.euv.proffme.model.entity.lesson.LessonEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface LessonService {
    LessonEntity findById(Long id) throws EntityNotFoundException;

    List<LessonEntity> findAll();

    LessonEntity save(LessonEntity lesson) throws EntityAlreadySavedException;

    LessonEntity update(LessonEntity lesson) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}