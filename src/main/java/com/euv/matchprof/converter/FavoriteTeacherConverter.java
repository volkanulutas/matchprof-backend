package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.favorite.FavoriteTeacherDto;
import com.euv.matchprof.model.dto.user.StudentDto;
import com.euv.matchprof.model.entity.favorite.FavoriteTeacherEntity;
import com.euv.matchprof.model.entity.user.StudentEntity;
import com.euv.matchprof.model.entity.user.TeacherEntity;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.StudentService;
import com.euv.matchprof.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class FavoriteTeacherConverter {
    private final TeacherService teacherService;

    private final StudentService studentService;

    private final StudentConverter studentConverter;

    private final ModelMapper modelMapper;

    @Autowired
    public FavoriteTeacherConverter(TeacherService teacherService, StudentService studentService, StudentConverter studentConverter) {
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.studentConverter = studentConverter;
        modelMapper = new ModelMapper();
    }

    public FavoriteTeacherEntity toEntity(FavoriteTeacherDto source) {
        FavoriteTeacherEntity target = new FavoriteTeacherEntity();
        modelMapper.map(source, target);
        Set<TeacherEntity> favoriteTeachers = null;
        Set<Long> favoriteTeacherIds = source.getFavoriteTeacherIds();
        if (!favoriteTeacherIds.isEmpty()) {
            favoriteTeachers = new HashSet<>();
            for (Long favouriteTeacherId : favoriteTeacherIds) {
                TeacherEntity teacher = null;
                try {
                    teacher = teacherService.findById(favouriteTeacherId);
                } catch (EntityNotFoundException e) {
                    log.error("Teacher is not found related to favorite teacher.");
                }
                favoriteTeachers.add(teacher);
            }
        }
        target.setFavoriteTeachers(favoriteTeachers);
        StudentEntity student = null;
        try {
            student = studentService.findById(source.getStudent().getId());
        } catch (EntityNotFoundException e) {
            log.error("Student is not found related to favorite teacher.");
        }
        target.setStudent(student);
        return target;
    }

    public FavoriteTeacherDto toDto(FavoriteTeacherEntity source) {
        FavoriteTeacherDto target = new FavoriteTeacherDto();
        modelMapper.map(source, target);
        Set<Long> favoriteTeacherIds = new HashSet<>();
        Set<TeacherEntity> favoriteTeachers = source.getFavoriteTeachers();
        for (TeacherEntity teacherEntity : favoriteTeachers) {
            favoriteTeacherIds.add(teacherEntity.getId());
        }
        target.setFavoriteTeacherIds(favoriteTeacherIds);
        StudentEntity studentEntity = source.getStudent();
        StudentDto student = studentConverter.toDto(studentEntity);
        target.setStudent(student);
        return target;
    }
}
