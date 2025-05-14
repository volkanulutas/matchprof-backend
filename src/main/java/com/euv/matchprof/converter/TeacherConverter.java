package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.user.TeacherDto;
import com.euv.matchprof.model.entity.user.TeacherEntity;
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
