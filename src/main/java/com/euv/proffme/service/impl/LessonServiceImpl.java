package com.euv.proffme.service.impl;

import com.euv.proffme.model.entity.lesson.LessonEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;
import com.euv.proffme.repository.LessonRepository;
import com.euv.proffme.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {this.lessonRepository = lessonRepository;}

    @Override
    public LessonEntity findById(Long id) throws EntityNotFoundException {
        Optional<LessonEntity> optional = lessonRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Lesson is not found: " + id);
    }

    @Override
    public List<LessonEntity> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public LessonEntity save(LessonEntity lesson) throws EntityAlreadySavedException {
        try {
            LessonEntity savedLesson = this.findById(lesson.getId());
            throw new EntityAlreadySavedException("Lesson is already saved before: " + lesson.getId());
        } catch (EntityNotFoundException e) {
            return lessonRepository.save(lesson);
        }
    }

    @Override
    public LessonEntity update(LessonEntity lesson) throws EntityNotFoundException {
        try {
            LessonEntity savedLesson = this.findById(lesson.getId());
            savedLesson.setId(lesson.getId());
            savedLesson.setName(lesson.getName());
            savedLesson.setCategory(lesson.getCategory());
            savedLesson.setDescription(lesson.getDescription());
            savedLesson.setCharge(lesson.getCharge());
            savedLesson.setEnabled(lesson.isEnabled());
            return this.lessonRepository.save(savedLesson);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            LessonEntity savedLesson = this.findById(id);
            this.lessonRepository.delete(savedLesson);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
