package com.euv.proffme.service;

import com.euv.proffme.model.entity.user.TeacherEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface TeacherService {
    TeacherEntity findById(Long id) throws EntityNotFoundException;

    List<TeacherEntity> findAll();

    TeacherEntity save(TeacherEntity teacher) throws EntityAlreadySavedException;;

    TeacherEntity update(TeacherEntity teacher) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
