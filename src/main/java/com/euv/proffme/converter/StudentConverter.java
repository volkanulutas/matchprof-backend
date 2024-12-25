package com.euv.proffme.converter;

import com.euv.proffme.model.dto.user.StudentDto;
import com.euv.proffme.model.entity.user.StudentEntity;
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
