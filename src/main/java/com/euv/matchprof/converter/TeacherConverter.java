package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.user.TeacherDto;
import com.euv.matchprof.model.entity.lesson.LessonEntity;
import com.euv.matchprof.model.entity.user.TeacherEntity;
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
public class TeacherConverter {
    private final LessonService lessonService;

    private final ModelMapper modelMapper;

    @Autowired
    public TeacherConverter(LessonService lessonService) {
        this.lessonService = lessonService;
        modelMapper = new ModelMapper();
    }

    public TeacherEntity toEntity(TeacherDto source) {
        TeacherEntity target = new TeacherEntity();
        modelMapper.map(source, target);
        Set<LessonEntity> givenLessons = new HashSet<>();
        Set<Long> givenLessonIds = source.getGivenLessonIds();
        for (Long lessonId : givenLessonIds) {
            LessonEntity lessonEntity = null;
            try {
                lessonEntity = lessonService.findById(lessonId);
            } catch (EntityNotFoundException e) {
                log.error("Lesson is not found related to teacher.");
            }
            givenLessons.add(lessonEntity);
        }
        target.setGivenLessons(givenLessons);
        return target;
    }

    public TeacherDto toDto(TeacherEntity source) {
        TeacherDto target = new TeacherDto();
        modelMapper.map(source, target);
        Set<LessonEntity> givenLessons = source.getGivenLessons();
        Set<Long> givenLessonIds = new HashSet<>();
        for (LessonEntity lesson : givenLessons) {
            givenLessonIds.add(lesson.getId());
        }
        target.setGivenLessonIds(givenLessonIds);
        return target;
    }
}
