package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.favorite.FavoriteTeacherEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.FavoriteTeacherRepository;
import com.euv.matchprof.service.FavoriteTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteTeacherServiceImpl implements FavoriteTeacherService {
    private final FavoriteTeacherRepository favoriteTeacherRepository;

    @Autowired
    public FavoriteTeacherServiceImpl(FavoriteTeacherRepository favoriteTeacherRepository) {
        this.favoriteTeacherRepository = favoriteTeacherRepository;
    }

    @Override
    public FavoriteTeacherEntity findById(Long id) throws EntityNotFoundException {
        Optional<FavoriteTeacherEntity> optional = favoriteTeacherRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Lesson is not found: " + id);
    }

    @Override
    public List<FavoriteTeacherEntity> findAll() {
        return favoriteTeacherRepository.findAll();
    }

    @Override
    public FavoriteTeacherEntity save(FavoriteTeacherEntity favoriteTeacher) throws EntityAlreadySavedException {
        try {
            FavoriteTeacherEntity savedFavoriteTeacher = this.findById(favoriteTeacher.getId());
            throw new EntityAlreadySavedException("Lesson is already saved before: " + favoriteTeacher.getId());
        } catch (EntityNotFoundException e) {
            return favoriteTeacherRepository.save(favoriteTeacher);
        }
    }

    @Override
    public FavoriteTeacherEntity update(FavoriteTeacherEntity favoriteTeacher) throws EntityNotFoundException {
        try {
            FavoriteTeacherEntity savedFavoriteTeacher = this.findById(favoriteTeacher.getId());
            savedFavoriteTeacher.setId(favoriteTeacher.getId());
            savedFavoriteTeacher.setFavoriteTeachers(favoriteTeacher.getFavoriteTeachers());
            savedFavoriteTeacher.setStudent(favoriteTeacher.getStudent());
            return this.favoriteTeacherRepository.save(savedFavoriteTeacher);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            FavoriteTeacherEntity savedFavoriteTeacher = this.findById(id);
            this.favoriteTeacherRepository.delete(savedFavoriteTeacher);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
