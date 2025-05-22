package com.euv.matchprof.config;

import com.euv.matchprof.converter.TeacherConverter;
import com.euv.matchprof.model.dto.user.TeacherDto;
import com.euv.matchprof.model.dto.user.TeacherListDto;
import com.euv.matchprof.model.entity.user.TeacherEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.service.TeacherService;
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
@Order(5)
public class TeacherYamlLoader {
    private ObjectMapper objectMapper;

    private final TeacherService teacherService;

    private final TeacherConverter teacherConverter;

    @Autowired
    public TeacherYamlLoader(TeacherService teacherService, TeacherConverter teacherConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    @Transactional
    @PostConstruct
    public void afterStart() {
        if(teacherService.hasAnyRecord()) return;
        File yamlFile = new File("src/main/resources/mock-teachers.yaml");
        try {
            TeacherListDto teacherList = objectMapper.readValue(yamlFile, TeacherListDto.class);
            List<TeacherDto> teachers = teacherList.getTeachers();
            for (TeacherDto teacher : teachers) {
                TeacherEntity teacherEntity = teacherConverter.toEntity(teacher);
                teacherEntity.setId(null);
                teacherService.save(teacherEntity);
            }
        } catch (IOException e) {
            log.error("Error is occurred while reading teachers. Detail: ", e);
        } catch (EntityAlreadySavedException e) {
            log.error("Error is occurred while saving teachers Detail: ", e);
        }
    }
}