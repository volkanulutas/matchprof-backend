package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.user.StudentDto;
import com.euv.matchprof.model.entity.user.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public StudentEntity toEntity(StudentDto source) {
        StudentEntity target = new StudentEntity();
        return target;
    }

    public StudentDto toDto(StudentEntity source) {
        StudentDto target = new StudentDto();
        return target;
    }
}
