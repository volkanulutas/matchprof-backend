package com.euv.matchprof.config;

import com.euv.matchprof.converter.LessonCategoryConverter;
import com.euv.matchprof.model.dto.lesson.LessonCategoryDto;
import com.euv.matchprof.model.dto.lesson.LessonCategoryListDto;
import com.euv.matchprof.model.entity.lesson.LessonCategoryEntity;
import com.euv.matchprof.service.LessonCategoryService;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@Order(1)
public class LessonCategoryYamlLoader {
    private ObjectMapper objectMapper;

    private final LessonCategoryService lessonCategoryService;

    private final LessonCategoryConverter lessonCategoryConverter;

    @Autowired
    public LessonCategoryYamlLoader(LessonCategoryService lessonCategoryService, LessonCategoryConverter lessonCategoryConverter) {
        this.lessonCategoryConverter = lessonCategoryConverter;
        objectMapper = new ObjectMapper(new YAMLFactory());
        this.lessonCategoryService = lessonCategoryService;
    }

    @Transactional
    @PostConstruct
    public void afterStart() {
        if(lessonCategoryService.hasAnyRecord()) return;
        try {
            File yamlFile = new File("src/main/resources/lesson-categories.yaml");
            LessonCategoryListDto lessonCategoryList = objectMapper.readValue(yamlFile, LessonCategoryListDto.class);
            List<LessonCategoryDto> lessonCategories = lessonCategoryList.getCategories();
            for (LessonCategoryDto lessonCategory : lessonCategories) {
                LessonCategoryEntity parentEntity = lessonCategoryConverter.toEntity(lessonCategory);
                parentEntity.setId(null);
                parentEntity.setChildCategories(Collections.emptySet());
                parentEntity = lessonCategoryService.save(parentEntity);
                Set<LessonCategoryEntity> childCategories = new HashSet<>();
                for (LessonCategoryDto child : lessonCategory.getChildCategories()) {
                    LessonCategoryEntity childEntity = lessonCategoryConverter.toEntity(child);
                    childEntity.setId(null);
                    childEntity.setParentCategory(parentEntity);
                    childEntity = lessonCategoryService.save(childEntity);
                    childCategories.add(childEntity);
                }
                parentEntity.setChildCategories(childCategories);
                parentEntity = lessonCategoryService.update(parentEntity);
            }
        } catch (IOException e) {
            log.error("Error is occurred while reading child category. Detail: ", e);
        } catch (Exception e) {
            log.error("Error is occurred while saving child category. Detail: ", e);
        }
    }
}
