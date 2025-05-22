package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.lesson.LessonChargeDto;
import com.euv.matchprof.model.entity.lesson.LessonChargeEntity;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LessonChargeConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public LessonChargeConverter() {
        modelMapper = new ModelMapper();
    }

    public LessonChargeEntity toEntity(LessonChargeDto source) {
        LessonChargeEntity target = new LessonChargeEntity();
        modelMapper.map(source, target);
        return target;
    }

    public LessonChargeDto toDto(LessonEntity source) {
        LessonChargeDto target = new LessonChargeDto();
        modelMapper.map(source, target);
        return target;
    }
}
