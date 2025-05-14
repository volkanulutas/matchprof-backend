package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.comment.CommentEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.CommentRepository;
import com.euv.matchprof.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {this.commentRepository = commentRepository;}

    @Override
    public CommentEntity findById(Long id) throws EntityNotFoundException {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Lesson is not found: " + id);
    }

    @Override
    public List<CommentEntity> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public CommentEntity save(CommentEntity comment) throws EntityAlreadySavedException {
        try {
            CommentEntity savedComment = this.findById(comment.getId());
            throw new EntityAlreadySavedException("Lesson is already saved before: " + comment.getId());
        } catch (EntityNotFoundException e) {
            return commentRepository.save(comment);
        }
    }

    @Override
    public CommentEntity update(CommentEntity comment) throws EntityNotFoundException {
        try {
            CommentEntity savedComment = this.findById(comment.getId());
            savedComment.setId(comment.getId());
            savedComment.setTeacher(comment.getTeacher());
            savedComment.setStudent(comment.getStudent());
            savedComment.setDate(comment.getDate());
            savedComment.setCommentText(comment.getCommentText());
            savedComment.setEnabled(comment.isEnabled());
            return this.commentRepository.save(savedComment);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            CommentEntity savedComment = this.findById(id);
            this.commentRepository.delete(savedComment);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
