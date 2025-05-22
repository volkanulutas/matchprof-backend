package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.lesson.LessonDto;
import com.euv.matchprof.model.entity.lesson.LessonCategoryEntity;
import com.euv.matchprof.model.entity.lesson.LessonChargeEntity;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.LessonCategoryService;
import com.euv.matchprof.service.LessonChargeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LessonConverter {
    private final LessonCategoryService lessonCategoryService;

    private final LessonChargeService lessonChargeService;

    private final ModelMapper modelMapper;

    @Autowired
    public LessonConverter(LessonCategoryService lessonCategoryService, LessonChargeService lessonChargeService) {
        this.lessonCategoryService = lessonCategoryService;
        this.lessonChargeService = lessonChargeService;
        modelMapper = new ModelMapper();
    }

    public LessonEntity toEntity(LessonDto source) {
        LessonEntity target = new LessonEntity();
        modelMapper.map(source, target);
        try {
            LessonCategoryEntity lessonCategory = lessonCategoryService.findById(source.getCategoryId());
            target.setCategory(lessonCategory);
        } catch (EntityNotFoundException e) {
            log.error("Lesson category is not found related to lesson. Detail: ", e);
        }
        try {
            LessonChargeEntity chargeEntity = lessonChargeService.findById(source.getChargeId());
            target.setCharge(chargeEntity);
        } catch (EntityNotFoundException e) {
            log.error("Lesson Charge is not found related to lesson. Detail: ", e);
        }
        return target;
    }

    public LessonDto toDto(LessonEntity source) {
        LessonDto target = new LessonDto();
        modelMapper.map(source, target);
        return target;
    }
}
