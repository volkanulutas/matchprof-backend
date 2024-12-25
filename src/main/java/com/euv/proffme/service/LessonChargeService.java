package com.euv.proffme.service;

import com.euv.proffme.model.entity.lesson.LessonChargeEntity;
import com.euv.proffme.model.entity.lesson.LessonEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface LessonChargeService {
    LessonChargeEntity findById(Long id) throws EntityNotFoundException;

    List<LessonChargeEntity> findAll();

    LessonChargeEntity save(LessonChargeEntity lessonCharge) throws EntityAlreadySavedException;;

    LessonChargeEntity update(LessonChargeEntity lessonCharge) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}