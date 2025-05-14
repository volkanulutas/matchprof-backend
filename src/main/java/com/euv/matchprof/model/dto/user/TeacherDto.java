package com.euv.matchprof.model.dto.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TeacherDto extends UserDto {
    private Set<Long> givenLessonIds = new HashSet<>();

    private int studentNumber;

    private double chargeByHour;

    private int replyTimeToRequests;

    private long birthdate;
}
