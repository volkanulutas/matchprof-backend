package com.euv.proffme.model.dto.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDto extends UserDto {
    private Set<Long> givenLessonIds = new HashSet<>();
}
