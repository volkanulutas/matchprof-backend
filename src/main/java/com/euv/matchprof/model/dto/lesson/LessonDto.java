package com.euv.matchprof.model.dto.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LessonDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("enabled")
    private boolean isEnabled;

    @JsonProperty("categoryId")
    private Long categoryId;

    @JsonProperty("chargeId")
    private Long chargeId;
}
