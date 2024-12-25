package com.euv.proffme.service;

import com.euv.proffme.model.entity.user.UserEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface UserService {
    UserEntity findById(Long id) throws EntityNotFoundException;

    List<UserEntity> findAll();

    UserEntity save(UserEntity user) throws EntityAlreadySavedException;;

    UserEntity update(UserEntity user) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
