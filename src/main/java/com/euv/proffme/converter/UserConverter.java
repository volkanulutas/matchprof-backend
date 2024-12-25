package com.euv.proffme.converter;

import com.euv.proffme.model.dto.user.UserDto;
import com.euv.proffme.model.entity.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity toEntity(UserDto source) {
        UserEntity target = new UserEntity();
        return target;
    }

    public UserDto toDto(UserEntity source) {
        UserDto target = new UserDto();
        return target;
    }
}
