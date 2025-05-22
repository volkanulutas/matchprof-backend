package com.euv.matchprof.model.dto.lesson;

import lombok.Data;

import java.util.List;

@Data
public class LessonCategoryListDto {
    private List<LessonCategoryDto> categories;
}
