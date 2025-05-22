package com.euv.matchprof.config;

import com.euv.matchprof.converter.LessonChargeConverter;
import com.euv.matchprof.model.dto.lesson.LessonChargeDto;
import com.euv.matchprof.model.dto.lesson.LessonChargeListDto;
import com.euv.matchprof.model.entity.lesson.LessonChargeEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.service.LessonChargeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@Order(2)
public class LessonChargeYamlLoader {
    private ObjectMapper objectMapper;

    private final LessonChargeService lessonChargeService;

    private final LessonChargeConverter lessonChargeConverter;

    @Autowired
    public LessonChargeYamlLoader(LessonChargeService lessonChargeService, LessonChargeConverter lessonChargeConverter) {
        this.lessonChargeService = lessonChargeService;
        this.lessonChargeConverter = lessonChargeConverter;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    @Transactional
    @PostConstruct
    public void afterStart() {
        if(lessonChargeService.hasAnyRecord()) return;
        File yamlFile = new File("src/main/resources/mock-charges.yaml");
        try {
            LessonChargeListDto lessonChargeList = objectMapper.readValue(yamlFile, LessonChargeListDto.class);
            List<LessonChargeDto> lessonCharges = lessonChargeList.getCharges();
            for (LessonChargeDto lessonCharge : lessonCharges) {
                LessonChargeEntity lessonChargeEntity = lessonChargeConverter.toEntity(lessonCharge);
                lessonChargeEntity.setId(null);
                lessonChargeService.save(lessonChargeEntity);
            }
        } catch (IOException e) {
            log.error("Error is occurred while reading lesson charges. Detail: ", e);
        } catch (EntityAlreadySavedException e) {
            log.error("Error is occurred while saving lesson charges. Detail: ", e);
        }
    }
}
