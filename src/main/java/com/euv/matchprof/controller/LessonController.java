package com.euv.matchprof.controller;

import com.euv.matchprof.config.SwaggerConfig;
import com.euv.matchprof.converter.LessonConverter;
import com.euv.matchprof.model.dto.lesson.LessonDto;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.LessonService;
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
@RequestMapping("api/lesson")
@Api(tags = SwaggerConfig.LESSON_TAG)
public class LessonController {
    private final LessonService lessonService;

    private final LessonConverter lessonConverter;

    @Autowired
    public LessonController(LessonService lessonService, LessonConverter lessonConverter) {
        this.lessonService = lessonService;
        this.lessonConverter = lessonConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<LessonEntity> lessonEntityList = lessonService.findAll();
            List<LessonDto> lessonDtoList = lessonEntityList.stream().map(e -> lessonConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(lessonDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting lessons.", e);
            return ResponseEntity.internalServerError().body("An internal server error in OMI Server");
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            LessonEntity lesson = lessonService.findById(id);
            return ResponseEntity.ok(lesson);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting lesson by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody LessonDto lessonDto) {
        try {
            LessonEntity lessonEntity = lessonConverter.toEntity(lessonDto);
            lessonEntity = lessonService.save(lessonEntity);
            return ResponseEntity.ok(lessonEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting lesson by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody LessonDto lessonDto) {
        try {
            LessonEntity lessonEntity = lessonConverter.toEntity(lessonDto);
            lessonEntity = lessonService.update(lessonEntity);
            return ResponseEntity.ok(lessonEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating lesson.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            LessonEntity lessonEntity = lessonService.findById(id);
            return ResponseEntity.ok(lessonEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating lesson.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
