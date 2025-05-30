package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.message.MessageEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.MessageRepository;
import com.euv.matchprof.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {this.messageRepository = messageRepository;}

    @Override
    public MessageEntity findById(Long id) throws EntityNotFoundException {
        Optional<MessageEntity> optional = messageRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Message is not found: " + id);
    }

    @Override
    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public MessageEntity save(MessageEntity message) throws EntityAlreadySavedException {
        if (message.getId() != null && messageRepository.existsById(message.getId())) {
            throw new EntityAlreadySavedException("Message is already saved before: " + message.getId());
        }
        return messageRepository.save(message);
    }

    @Override
    public MessageEntity update(MessageEntity message) throws EntityNotFoundException {
        try {
            MessageEntity savedMessage = this.findById(message.getId());
            savedMessage.setId(message.getId());
            savedMessage.setMessage(message.getMessage());
            savedMessage.setTaker(message.getTaker());
            savedMessage.setDateTime(message.getDateTime());
            savedMessage.setSender(message.getSender());
            return this.messageRepository.save(savedMessage);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            MessageEntity savedMessage = this.findById(id);
            this.messageRepository.delete(savedMessage);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public boolean hasAnyRecord() {
        return messageRepository.existsByIdIsNotNull();
    }
}
