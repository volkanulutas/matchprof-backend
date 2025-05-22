package com.euv.matchprof.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("password")
    private String password;

    @JsonProperty("profilePicture")
    private String profilePicture;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("enabled")
    private boolean isEnabled;

    @JsonProperty("takenLessonIds")
    private Set<Long> takenLessonIds = new HashSet<>();
}
