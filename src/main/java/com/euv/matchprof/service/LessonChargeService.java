package com.euv.matchprof.service;

import com.euv.matchprof.model.entity.lesson.LessonChargeEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;

import java.util.List;

public interface LessonChargeService {
    LessonChargeEntity findById(Long id) throws EntityNotFoundException;

    List<LessonChargeEntity> findAll();

    LessonChargeEntity save(LessonChargeEntity lessonCharge) throws EntityAlreadySavedException;;

    LessonChargeEntity update(LessonChargeEntity lessonCharge) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    boolean hasAnyRecord();
}