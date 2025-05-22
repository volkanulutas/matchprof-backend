package com.euv.matchprof.config;

import com.euv.matchprof.converter.StudentConverter;
import com.euv.matchprof.model.dto.user.StudentDto;
import com.euv.matchprof.model.dto.user.StudentListDto;
import com.euv.matchprof.model.entity.user.StudentEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.service.StudentService;
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
@Order(4)
public class StudentYamlLoader {
    private ObjectMapper objectMapper;

    private final StudentService studentService;

    private final StudentConverter studentConverter;

    @Autowired
    public StudentYamlLoader(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
        objectMapper = new ObjectMapper(new YAMLFactory());
    }

    @Transactional
    @PostConstruct
    public void afterStart() {
        if(studentService.hasAnyRecord()) return;
        File yamlFile = new File("src/main/resources/mock-students.yaml");
        try {
            StudentListDto studentList = objectMapper.readValue(yamlFile, StudentListDto.class);
            List<StudentDto> students = studentList.getStudents();
            for (StudentDto student : students) {
                StudentEntity studentEntity = studentConverter.toEntity(student);
                studentEntity.setId(null);
                studentService.save(studentEntity);
            }
        } catch (IOException e) {
            log.error("Error is occurred while reading students. Detail: ", e);
        } catch (EntityAlreadySavedException e) {
            log.error("Error is occurred while saving students Detail: ", e);
        }
    }
}
