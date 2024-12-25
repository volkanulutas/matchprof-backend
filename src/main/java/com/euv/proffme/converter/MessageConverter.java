package com.euv.proffme.converter;

import com.euv.proffme.model.dto.message.MessageDto;
import com.euv.proffme.model.entity.message.MessageEntity;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {
    public MessageEntity toEntity(MessageDto source) {
        MessageEntity target = new MessageEntity();
        return target;
    }

    public MessageDto toDto(MessageEntity source) {
        MessageDto target = new MessageDto();
        return target;
    }
}
