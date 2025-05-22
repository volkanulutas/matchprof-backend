package com.euv.matchprof.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class LessonCategoryDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("enabled")
    private boolean isEnabled;

    @JsonProperty("childCategories")
    private Set<LessonCategoryDto> childCategories;
}