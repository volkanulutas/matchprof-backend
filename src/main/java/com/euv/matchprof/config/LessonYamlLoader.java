package com.euv.matchprof.config;

import com.euv.matchprof.converter.LessonConverter;
import com.euv.matchprof.model.dto.lesson.LessonDto;
import com.euv.matchprof.model.dto.lesson.LessonListDto;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.service.LessonService;
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
@Order(3)
public class LessonYamlLoader {
    private ObjectMapper objectMapper;

    private final LessonService lessonService;

    private final LessonConverter lessonConverter;

    @Autowired
    public LessonYamlLoader(LessonService lessonService, LessonConverter lessonConverter) {
        this.lessonService = lessonService;
        this.lessonConverter = lessonConverter;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    @Transactional
    @PostConstruct
    public void afterStart() {
        if (lessonService.hasAnyRecord()) {
            return;
        }
        File yamlFile = new File("src/main/resources/mock-lessons.yaml");
        try {
            LessonListDto lessonList = objectMapper.readValue(yamlFile, LessonListDto.class);
            List<LessonDto> lessons = lessonList.getLessons();
            for (LessonDto lesson : lessons) {
                LessonEntity lessonEntity = lessonConverter.toEntity(lesson);
                lessonEntity.setId(null);
                lessonService.save(lessonEntity);
            }
        } catch (IOException e) {
            log.error("Error is occurred while reading lessons. Detail: ", e);
        } catch (EntityAlreadySavedException e) {
            log.error("Error is occurred while saving lessons Detail: ", e);
        }
    }
}