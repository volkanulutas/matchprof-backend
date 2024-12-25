package com.euv.proffme.controller;

import com.euv.proffme.config.SwaggerConfig;
import com.euv.proffme.converter.CommentConverter;
import com.euv.proffme.model.dto.comment.CommentDto;
import com.euv.proffme.model.entity.comment.CommentEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;
import com.euv.proffme.service.CommentService;
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
@RequestMapping("api/comment")
@Api(tags = SwaggerConfig.COMMENT_TAG)
public class CommentController {
    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @Autowired
    public CommentController(CommentService commentService, CommentConverter commentConverter) {
        this.commentService = commentService;
        this.commentConverter = commentConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<CommentEntity> commentEntityList = commentService.findAll();
            List<CommentDto> lessonDtoList = commentEntityList.stream().map(e -> commentConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(lessonDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting comments.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            CommentEntity commentEntity = commentService.findById(id);
            return ResponseEntity.ok(commentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting comment by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody CommentDto commentDto) {
        try {
            CommentEntity commentEntity = commentConverter.toEntity(commentDto);
            commentEntity = commentService.save(commentEntity);
            return ResponseEntity.ok(commentEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting comment by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody CommentDto commentDto) {
        try {
            CommentEntity commentEntity = commentConverter.toEntity(commentDto);
            commentEntity = commentService.update(commentEntity);
            return ResponseEntity.ok(commentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating comment.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            CommentEntity commentEntity = commentService.findById(id);
            return ResponseEntity.ok(commentEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating comment.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
