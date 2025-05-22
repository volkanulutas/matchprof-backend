package com.euv.matchprof.model.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class StudentListDto {
    private List<StudentDto> students;
}
