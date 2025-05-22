package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.lesson.LessonEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.LessonRepository;
import com.euv.matchprof.service.LessonService;
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
        if (id == null) {
            throw new EntityNotFoundException("Lesson is not found: " + id);
        }
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
        if (lesson.getId() != null && lessonRepository.existsById(lesson.getId())) {
            throw new EntityAlreadySavedException("Lesson is already saved before: " + lesson.getId());
        }
        return lessonRepository.save(lesson);
    }

    @Override
    public LessonEntity update(LessonEntity lesson) throws EntityNotFoundException {
        try {
            LessonEntity savedLesson = this.findById(lesson.getId());
            savedLesson.setId(lesson.getId());
            savedLesson.setName(lesson.getName());
            savedLesson.setDescription(lesson.getDescription());
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

    @Override
    public boolean hasAnyRecord() {
        return lessonRepository.existsByIdIsNotNull();
    }
}
