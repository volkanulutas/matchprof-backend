package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.lesson.LessonCategoryDto;
import com.euv.matchprof.model.entity.lesson.LessonCategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LessonCategoryConverter {
    private ModelMapper modelMapper;

    public LessonCategoryConverter() {
        modelMapper = new ModelMapper();
    }

    public LessonCategoryEntity toEntity(LessonCategoryDto source) {
        LessonCategoryEntity target = new LessonCategoryEntity();
        modelMapper.map(source, target);
        return target;
    }

    public LessonCategoryDto toDto(LessonCategoryEntity source) {
        LessonCategoryDto target = new LessonCategoryDto();
        modelMapper.map(source, target);
        return target;
    }
}
