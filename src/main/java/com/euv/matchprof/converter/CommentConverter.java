package com.euv.matchprof.converter;

import com.euv.matchprof.model.dto.comment.CommentDto;
import com.euv.matchprof.model.entity.comment.CommentEntity;
import com.euv.matchprof.service.StudentService;
import com.euv.matchprof.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    private final TeacherService teacherService;

    private final StudentService studentService;

    @Autowired
    public CommentConverter(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public CommentEntity toEntity(CommentDto source) {
        CommentEntity target = new CommentEntity();
        target.setId(source.getId());
        target.setCommentText(source.getCommentText());
        target.setDate(source.getDate());
        target.setEnabled(source.isEnabled());
        // target.setTeacher();
        // target.setStudent();
        return target;
    }

    public CommentDto toDto(CommentEntity source) {
        CommentDto target = new CommentDto();
        target.setId(source.getId());
        target.setCommentText(source.getCommentText());
        target.setDate(source.getDate());
        target.setTeacherId(source.getTeacher().getId());
        target.setStudentId(source.getStudent().getId());
        target.setEnabled(source.isEnabled());
        return target;
    }
}
