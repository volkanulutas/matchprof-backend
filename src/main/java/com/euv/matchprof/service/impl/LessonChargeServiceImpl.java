package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.lesson.LessonChargeEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.LessonChargeRepository;
import com.euv.matchprof.service.LessonChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonChargeServiceImpl implements LessonChargeService {
    private final LessonChargeRepository lessonChargeRepository;

    @Autowired
    public LessonChargeServiceImpl(LessonChargeRepository lessonChargeRepository) {this.lessonChargeRepository = lessonChargeRepository;}

    @Override
    public LessonChargeEntity findById(Long id) throws EntityNotFoundException {
        Optional<LessonChargeEntity> optional = lessonChargeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Lesson is not found: " + id);
    }

    @Override
    public List<LessonChargeEntity> findAll() {
        return lessonChargeRepository.findAll();
    }

    @Override
    public LessonChargeEntity save(LessonChargeEntity lessonCharge) throws EntityAlreadySavedException {
        try {
            LessonChargeEntity savedLesson = this.findById(lessonCharge.getId());
            throw new EntityAlreadySavedException("Lesson is already saved before: " + lessonCharge.getId());
        } catch (EntityNotFoundException e) {
            return lessonChargeRepository.save(lessonCharge);
        }
    }

    @Override
    public LessonChargeEntity update(LessonChargeEntity lessonCharge) throws EntityNotFoundException {
        try {
            LessonChargeEntity savedLessonCharge = this.findById(lessonCharge.getId());
            savedLessonCharge.setId(lessonCharge.getId());
            savedLessonCharge.setAmount(lessonCharge.getAmount());
            return this.lessonChargeRepository.save(savedLessonCharge);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            LessonChargeEntity lessonCharge = this.findById(id);
            this.lessonChargeRepository.delete(lessonCharge);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
