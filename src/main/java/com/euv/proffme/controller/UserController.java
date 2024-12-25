package com.euv.proffme.controller;

import com.euv.proffme.config.SwaggerConfig;
import com.euv.proffme.converter.UserConverter;
import com.euv.proffme.model.dto.user.UserDto;
import com.euv.proffme.model.entity.user.UserEntity;
import com.euv.proffme.model.exception.EntityAlreadySavedException;
import com.euv.proffme.model.exception.EntityNotFoundException;
import com.euv.proffme.service.UserService;
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
@RequestMapping("api/user")
@Api(tags = SwaggerConfig.USER_TAG)
public class UserController {
    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        try {
            List<UserEntity> userEntityList = userService.findAll();
            List<UserDto> teacherDtoList = userEntityList.stream().map(e -> userConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(teacherDtoList);
        } catch (Exception e) {
            log.error("An error occurred while getting users.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        try {
            UserEntity userEntity = userService.findById(id);
            return ResponseEntity.ok(userEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while getting user by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        try {
            UserEntity userEntity = userConverter.toEntity(userDto);
            userEntity = userService.save(userEntity);
            return ResponseEntity.ok(userEntity);
        } catch (EntityAlreadySavedException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("An error occurred while getting user by id.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        try {
            UserEntity userEntity = userConverter.toEntity(userDto);
            userEntity = userService.update(userEntity);
            return ResponseEntity.ok(userEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating user.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }

    @DeleteMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            UserEntity userEntity = userService.findById(id);
            return ResponseEntity.ok(userEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("An error occurred while updating user.", e);
            return ResponseEntity.internalServerError().body("An internal server error.");
        }
    }
}
