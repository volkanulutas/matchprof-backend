package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.user.StudentDto;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import com.euv.matchprof.model.entity.user.StudentEntity;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class StudentConverter {
    private final LessonService lessonService;

    private final ModelMapper modelMapper;

    @Autowired
    public StudentConverter(LessonService lessonService) {
        this.lessonService = lessonService;
        modelMapper = new ModelMapper();
    }

    public StudentEntity toEntity(StudentDto source) {
        StudentEntity target = new StudentEntity();
        modelMapper.map(source, target);
        Set<LessonEntity> takenLessons = new HashSet<>();
        Set<Long> takenLessonIds = source.getTakenLessonIds();
        for (Long takenLessonId : takenLessonIds) {
            try {
                LessonEntity lessonEntity = lessonService.findById(takenLessonId);
                takenLessons.add(lessonEntity);
            } catch (EntityNotFoundException e) {
                log.error("Taken lesson is not found related to student.");
            }
        }
        target.setTakenLessons(takenLessons);
        return target;
    }

    public StudentDto toDto(StudentEntity source) {
        StudentDto target = new StudentDto();
        modelMapper.map(source, target);
        Set<LessonEntity> takenLessons = source.getTakenLessons();
        Set<Long> takenLessonIds = new HashSet<>();
        for (LessonEntity lessonEntity : takenLessons) {
            takenLessonIds.add(lessonEntity.getId());
        }
        target.setTakenLessonIds(takenLessonIds);
        return target;
    }
}
