package com.euv.matchprof.controller;

import com.euv.matchprof.config.SwaggerConfig;
import com.euv.matchprof.converter.FavoriteTeacherConverter;
import com.euv.matchprof.model.dto.favorite.FavoriteTeacherDto;
import com.euv.matchprof.model.entity.favorite.FavoriteTeacherEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.FavoriteTeacherService;
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
@RequestMapping("api/favorite-teacher")
@Api(tags = SwaggerConfig.FAVORITE_TEACHER_TAG)
public class FavoriteTeacherController {
    private final FavoriteTeacherService favoriteTeacherService;

    private final FavoriteTeacherConverter favoriteTeacherConverter;

    @Autowired
    public FavoriteTeacherController(FavoriteTeacherService favoriteTeacherService, FavoriteTeacherConverter favoriteTeacherConverter) {
        this.favoriteTeacherService = favoriteTeacherService;
        this.favoriteTeacherConverter = favoriteTeacherConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<FavoriteTeacherEntity> favoriteTeacherDtoList = favoriteTeacherService.findAll();
            List<FavoriteTeacherDto> lessonDtoList =
                    favoriteTeacherDtoList.stream().map(e -> favoriteTeacherConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(lessonDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting favorite teacher.", e);
            return ResponseEntity.internalServerError().body("An internal server error in OMI Server");
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            FavoriteTeacherEntity lesson = favoriteTeacherService.findById(id);
            return ResponseEntity.ok(lesson);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting favorite teacher by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody FavoriteTeacherDto favoriteTeacherDto) {
        try {
            FavoriteTeacherEntity favoriteTeacherEntity = favoriteTeacherConverter.toEntity(favoriteTeacherDto);
            favoriteTeacherEntity = favoriteTeacherService.save(favoriteTeacherEntity);
            return ResponseEntity.ok(favoriteTeacherEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting favorite teacher by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody FavoriteTeacherDto favoriteTeacherDto) {
        try {
            FavoriteTeacherEntity favoriteTeacherEntity = favoriteTeacherConverter.toEntity(favoriteTeacherDto);
            favoriteTeacherEntity = favoriteTeacherService.update(favoriteTeacherEntity);
            return ResponseEntity.ok(favoriteTeacherEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating favorite teacher.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            FavoriteTeacherEntity favoriteTeacherEntity = favoriteTeacherService.findById(id);
            return ResponseEntity.ok(favoriteTeacherEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating favorite teacher.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
