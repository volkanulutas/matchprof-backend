package com.euv.matchprof.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TeacherDto {
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

    @JsonProperty("chargeByHour")
    private double chargeByHour;

    @JsonProperty("replyTimeToRequests")
    private int replyTimeToRequests;

    @JsonProperty("birthdate")
    private long birthdate;

    @JsonProperty("givenLessonIds")
    private Set<Long> givenLessonIds = new HashSet<>();
}
