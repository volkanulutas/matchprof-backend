package com.euv.matchprof.controller;

import com.euv.matchprof.config.SwaggerConfig;
import com.euv.matchprof.converter.MessageConverter;
import com.euv.matchprof.model.dto.message.MessageDto;
import com.euv.matchprof.model.entity.message.MessageEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.MessageService;
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
@RequestMapping("api/message")
@Api(tags = SwaggerConfig.MESSAGE_TAG)
public class MessageController {
    private final MessageService messageService;

    private final MessageConverter messageConverter;

    @Autowired
    public MessageController(MessageService messageService, MessageConverter messageConverter) {
        this.messageService = messageService;
        this.messageConverter = messageConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<MessageEntity> messageEntityList = messageService.findAll();
            List<MessageDto> lessonDtoList = messageEntityList.stream().map(e -> messageConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(lessonDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting messages.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            MessageEntity message = messageService.findById(id);
            return ResponseEntity.ok(message);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting message by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody MessageDto messageDto) {
        try {
            MessageEntity messageEntity = messageConverter.toEntity(messageDto);
            messageEntity = messageService.save(messageEntity);
            return ResponseEntity.ok(messageEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting message by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody MessageDto messageDto) {
        try {
            MessageEntity messageEntity = messageConverter.toEntity(messageDto);
            messageEntity = messageService.update(messageEntity);
            return ResponseEntity.ok(messageEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating message.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            MessageEntity messageEntity = messageService.findById(id);
            return ResponseEntity.ok(messageEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating message.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
