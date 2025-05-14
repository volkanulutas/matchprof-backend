package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.user.UserDto;
import com.euv.matchprof.model.entity.user.UserEntity;
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
