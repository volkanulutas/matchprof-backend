package com.euv.matchprof.service;

import com.euv.matchprof.model.entity.user.UserEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;

import java.util.List;

public interface UserService {
    UserEntity findById(Long id) throws EntityNotFoundException;

    List<UserEntity> findAll();

    UserEntity save(UserEntity user) throws EntityAlreadySavedException;;

    UserEntity update(UserEntity user) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    boolean hasAnyRecord();
}
