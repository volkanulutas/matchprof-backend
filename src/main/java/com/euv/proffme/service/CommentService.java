package com.euv.proffme.service;

import com.euv.proffme.model.entity.comment.CommentEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface CommentService {
    CommentEntity findById(Long id) throws EntityNotFoundException;

    List<CommentEntity> findAll();

    CommentEntity save(CommentEntity comment) throws EntityAlreadySavedException;

    CommentEntity update(CommentEntity comment) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
