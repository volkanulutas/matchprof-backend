package com.euv.proffme.service.impl;

import com.euv.proffme.model.entity.message.MessageEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;
import com.euv.proffme.repository.MessageRepository;
import com.euv.proffme.service.MessageService;
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
        throw new EntityNotFoundException("Lesson is not found: " + id);
    }

    @Override
    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public MessageEntity save(MessageEntity message) throws EntityAlreadySavedException {
        try {
            MessageEntity savedMessage = this.findById(message.getId());
            throw new EntityAlreadySavedException("Lesson is already saved before: " + message.getId());
        } catch (EntityNotFoundException e) {
            return messageRepository.save(message);
        }
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
}
