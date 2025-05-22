package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.comment.CommentDto;
import com.euv.matchprof.model.entity.comment.CommentEntity;
import com.euv.matchprof.model.entity.user.StudentEntity;
import com.euv.matchprof.model.entity.user.TeacherEntity;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.service.StudentService;
import com.euv.matchprof.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentConverter {
    private final TeacherService teacherService;

    private final StudentService studentService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public CommentConverter(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public CommentEntity toEntity(CommentDto source) {
        CommentEntity target = new CommentEntity();
        modelMapper.map(source, target);
        try {
            TeacherEntity teacherEntity = teacherService.findById(source.getTeacherId());
            target.setTeacher(teacherEntity);
        } catch (EntityNotFoundException e) {
            log.error("Teacher is not found related to the comment.");
        }
        try {
            StudentEntity studentEntity = studentService.findById(source.getStudentId());
            target.setStudent(studentEntity);
        } catch (EntityNotFoundException e) {
            log.error("Student is not found related to the comment.");
        }
        return target;
    }

    public CommentDto toDto(CommentEntity source) {
        CommentDto target = new CommentDto();
        modelMapper.map(source, target);
        TeacherEntity teacher = source.getTeacher();
        if (teacher != null) {
            target.setTeacherId(teacher.getId());
        }
        StudentEntity student = source.getStudent();
        if (student != null) {
            target.setStudentId(student.getId());
        }
        return target;
    }
}
