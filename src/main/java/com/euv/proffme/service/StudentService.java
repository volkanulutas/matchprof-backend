package com.euv.proffme.service;

import com.euv.proffme.model.entity.user.StudentEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface StudentService {
    StudentEntity findById(Long id) throws EntityNotFoundException;

    List<StudentEntity> findAll();

    StudentEntity save(StudentEntity student) throws EntityAlreadySavedException;;

    StudentEntity update(StudentEntity student) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
