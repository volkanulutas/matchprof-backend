package com.euv.proffme.model.dto.lesson;

import lombok.Data;

@Data
public class LessonCategoryDto {
    private Long id;

    private String name;

    private String description;

    private boolean isEnabled;
}
