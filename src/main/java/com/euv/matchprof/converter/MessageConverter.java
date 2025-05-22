package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.message.MessageDto;
import com.euv.matchprof.model.entity.message.MessageEntity;
import com.euv.matchprof.model.entity.user.UserEntity;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConverter {
    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public MessageConverter(UserService userService) {
        this.userService = userService;
        modelMapper = new ModelMapper();
    }

    public MessageEntity toEntity(MessageDto source) {
        MessageEntity target = new MessageEntity();
        modelMapper.map(source, target);
        Long takerId = source.getTakerId();
        Long senderId = source.getSenderId();
        try {
            UserEntity takerUser = userService.findById(takerId);
            target.setTaker(takerUser);
        } catch (EntityNotFoundException e) {
            log.error("Taker user is not found related to message.");
        }
        try {
            UserEntity senderUser = userService.findById(senderId);
            target.setSender(senderUser);
        } catch (EntityNotFoundException e) {
            log.error("Sender user is not found related to message.");
        }
        return target;
    }

    public MessageDto toDto(MessageEntity source) {
        MessageDto target = new MessageDto();
        modelMapper.map(source, target);
        UserEntity taker = source.getTaker();
        target.setTakerId(taker.getId());
        UserEntity sender = source.getSender();
        target.setSenderId(sender.getId());
        return target;
    }
}
