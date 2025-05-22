package com.euv.matchprof.service;

import com.euv.matchprof.model.entity.message.MessageEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;

import java.util.List;

public interface MessageService {
    MessageEntity findById(Long id) throws EntityNotFoundException;

    List<MessageEntity> findAll();

    MessageEntity save(MessageEntity message) throws EntityAlreadySavedException;;

    MessageEntity update(MessageEntity message) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    boolean hasAnyRecord();
}
