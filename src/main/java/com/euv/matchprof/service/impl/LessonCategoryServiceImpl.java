package com.euv.matchprof.service.impl;

import com.euv.matchprof.converter.LessonCategoryConverter;
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

    private final LessonCategoryConverter lessonCategoryConverter;

    @Autowired
    public LessonCategoryServiceImpl(LessonCategoryRepository lessonCategoryRepository, LessonCategoryConverter lessonCategoryConverter) {
        this.lessonCategoryRepository = lessonCategoryRepository;
        this.lessonCategoryConverter = lessonCategoryConverter;
    }

    @Override
    public LessonCategoryEntity findById(Long id) throws EntityNotFoundException {
        Optional<LessonCategoryEntity> optional = lessonCategoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Lesson Category is not found: " + id);
    }

    @Override
    public List<LessonCategoryEntity> findAll() {
        return lessonCategoryRepository.findAll();
    }

    @Override
    public LessonCategoryEntity save(LessonCategoryEntity lessonCategory) throws EntityAlreadySavedException {
        if (lessonCategory.getId() != null && lessonCategoryRepository.existsById(lessonCategory.getId())) {
            throw new EntityAlreadySavedException("Lesson Category is already saved before: " + lessonCategory.getId());
        }
        return lessonCategoryRepository.save(lessonCategory);
    }

    @Override
    public LessonCategoryEntity update(LessonCategoryEntity lessonCategory) throws EntityNotFoundException {
        try {
            LessonCategoryEntity current = this.findById(lessonCategory.getId());
            lessonCategory.setId(current.getId());
            return this.lessonCategoryRepository.save(lessonCategory);
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

    @Override
    public boolean hasAnyRecord() {
        return lessonCategoryRepository.existsByIdIsNotNull();
    }
}
