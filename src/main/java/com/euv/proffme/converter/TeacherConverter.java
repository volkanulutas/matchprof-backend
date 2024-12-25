package com.euv.proffme.converter;

import com.euv.proffme.model.dto.user.TeacherDto;
import com.euv.proffme.model.entity.user.TeacherEntity;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter {
    public TeacherEntity toEntity(TeacherDto source) {
        TeacherEntity target = new TeacherEntity();
        return target;
    }

    public TeacherDto toDto(TeacherEntity source) {
        TeacherDto target = new TeacherDto();
        return target;
    }
}
