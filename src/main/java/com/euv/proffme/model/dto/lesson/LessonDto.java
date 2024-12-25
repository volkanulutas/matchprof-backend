package com.euv.proffme.model.dto.lesson;

import lombok.Data;

@Data
public class LessonDto {
    private Long id;

    private String name;

    private String description;

    private boolean isEnabled;

    private Long chargeId;
}
