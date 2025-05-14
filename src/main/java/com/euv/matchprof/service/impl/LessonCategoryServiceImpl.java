package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.lesson.LessonCategoryEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.LessonCategoryRepository;
import com.euv.matchprof.service.LessonCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonCategoryServiceImpl implements LessonCategoryService {
    private final LessonCategoryRepository lessonCategoryRepository;

    @Autowired
    public LessonCategoryServiceImpl(LessonCategoryRepository lessonCategoryRepository) {this.lessonCategoryRepository = lessonCategoryRepository;}

    @Override
    public LessonCategoryEntity findById(Long id) throws EntityNotFoundException {
        Optional<LessonCategoryEntity> optional = lessonCategoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Lesson is not found: " + id);
    }

    @Override
    public List<LessonCategoryEntity> findAll() {
        return lessonCategoryRepository.findAll();
    }

    @Override
    public LessonCategoryEntity save(LessonCategoryEntity lessonCategory) throws EntityAlreadySavedException {
        try {
            LessonCategoryEntity savedLesson = this.findById(lessonCategory.getId());
            throw new EntityAlreadySavedException("Lesson is already saved before: " + lessonCategory.getId());
        } catch (EntityNotFoundException e) {
            return lessonCategoryRepository.save(lessonCategory);
        }
    }

    @Override
    public LessonCategoryEntity update(LessonCategoryEntity lessonCategory) throws EntityNotFoundException {
        try {
            LessonCategoryEntity savedLessonCategory = this.findById(lessonCategory.getId());
            savedLessonCategory.setId(lessonCategory.getId());
            savedLessonCategory.setName(lessonCategory.getName());
            savedLessonCategory.setDescription(lessonCategory.getDescription());
            savedLessonCategory.setEnabled(lessonCategory.isEnabled());
            savedLessonCategory.setLessons(lessonCategory.getLessons());
            return this.lessonCategoryRepository.save(savedLessonCategory);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            LessonCategoryEntity savedLessonCategory = this.findById(id);
            this.lessonCategoryRepository.delete(savedLessonCategory);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
