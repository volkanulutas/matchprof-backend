package com.euv.matchprof.controller;

import com.euv.matchprof.config.SwaggerConfig;
import com.euv.matchprof.converter.StudentConverter;
import com.euv.matchprof.model.dto.user.StudentDto;
import com.euv.matchprof.model.entity.user.StudentEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.StudentService;
import io.swagger.annotations.Api;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/student")
@Api(tags = SwaggerConfig.STUDENT_TAG)
public class StudentController {
    private final StudentService studentService;

    private final StudentConverter studentConverter;

    @Autowired
    public StudentController(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<StudentEntity> messageEntityList = studentService.findAll();
            List<StudentDto> studentDtoList = messageEntityList.stream().map(e -> studentConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(studentDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting students.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            StudentEntity studentEntity = studentService.findById(id);
            return ResponseEntity.ok(studentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting student by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto) {
        try {
            StudentEntity studentEntity = studentConverter.toEntity(studentDto);
            studentEntity = studentService.save(studentEntity);
            return ResponseEntity.ok(studentEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting student by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto) {
        try {
            StudentEntity studentEntity = studentConverter.toEntity(studentDto);
            studentEntity = studentService.update(studentEntity);
            return ResponseEntity.ok(studentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating student.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            StudentEntity studentEntity = studentService.findById(id);
            return ResponseEntity.ok(studentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating student.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
