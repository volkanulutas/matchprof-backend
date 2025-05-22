package com.euv.matchprof.model.entity.user;

import com.euv.matchprof.model.entity.lesson.LessonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "TeacherEntity")
@Table(name = "TeacherEntity")
public class TeacherEntity extends UserEntity {
    @Column(nullable = false)
    private int studentNumber;

    @Column(nullable = false)
    private double chargeByHour;

    @Column(nullable = false)
    private int replyTimeToRequests;

    @Column(nullable = false)
    private long birthdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_given_lessons", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<LessonEntity> givenLessons = new HashSet<>();
}
