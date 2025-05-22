package com.euv.matchprof.controller;

import com.euv.matchprof.config.SwaggerConfig;
import com.euv.matchprof.converter.TeacherConverter;
import com.euv.matchprof.model.dto.user.TeacherDto;
import com.euv.matchprof.model.entity.user.TeacherEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.TeacherService;
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
@RequestMapping("api/teacher")
@Api(tags = SwaggerConfig.TEACHER_TAG)
public class TeacherController {
    private final TeacherService teacherService;

    private final TeacherConverter teacherConverter;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherConverter teacherConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<TeacherEntity> teacherEntityList = teacherService.findAll();
            List<TeacherDto> teacherDtoList = teacherEntityList.stream().map(e -> teacherConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(teacherDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting teachers.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            TeacherEntity teacherEntity = teacherService.findById(id);
            return ResponseEntity.ok(teacherEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting teacher by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody TeacherDto teacherDto) {
        try {
            TeacherEntity teacherEntity = teacherConverter.toEntity(teacherDto);
            teacherEntity = teacherService.save(teacherEntity);
            return ResponseEntity.ok(teacherEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting teacher by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody TeacherDto teacherDto) {
        try {
            TeacherEntity teacherEntity = teacherConverter.toEntity(teacherDto);
            teacherEntity = teacherService.update(teacherEntity);
            return ResponseEntity.ok(teacherEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating teacher.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            TeacherEntity teacherEntity = teacherService.findById(id);
            return ResponseEntity.ok(teacherEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating teacher.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
