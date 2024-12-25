package com.euv.proffme.model.entity.user;

import com.euv.proffme.model.entity.LessonEntity;

import java.util.HashSet;
import java.util.Set;

public class Teacher extends User {
    private Set<LessonEntity> givenLessons = new HashSet<>();

    private int studentNumber;

    private double chargeByHour;

    private int replyTimeToRequests;

    private long birthdate;
}
