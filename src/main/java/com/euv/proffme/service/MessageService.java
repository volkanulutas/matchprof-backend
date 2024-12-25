package com.euv.proffme.service;

import com.euv.proffme.model.entity.message.MessageEntity;
import com.euv.proffme.model.entity.user.StudentEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;

import java.util.List;

public interface MessageService {
    MessageEntity findById(Long id) throws EntityNotFoundException;

    List<MessageEntity> findAll();

    MessageEntity save(MessageEntity message) throws EntityAlreadySavedException;;

    MessageEntity update(MessageEntity message) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;
}
