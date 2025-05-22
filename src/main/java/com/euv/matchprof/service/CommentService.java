package com.euv.matchprof.service;

import com.euv.matchprof.model.entity.comment.CommentEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;

import java.util.List;

public interface CommentService {
    CommentEntity findById(Long id) throws EntityNotFoundException;

    List<CommentEntity> findAll();

    CommentEntity save(CommentEntity comment) throws EntityAlreadySavedException;

    CommentEntity update(CommentEntity comment) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    boolean hasAnyRecord();
}
