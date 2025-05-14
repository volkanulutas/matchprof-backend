package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.lesson.LessonDto;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import org.springframework.stereotype.Component;

@Component
public class LessonConverter {
    public LessonEntity toEntity(LessonDto source) {
        LessonEntity target = new LessonEntity();
        return target;
    }

    public LessonDto toDto(LessonEntity source) {
        LessonDto target = new LessonDto();
        return target;
    }
}
