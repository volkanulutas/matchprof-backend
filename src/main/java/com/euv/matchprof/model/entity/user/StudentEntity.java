package com.euv.matchprof.model.entity.user;

import com.euv.matchprof.model.entity.lesson.LessonEntity;
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
@Entity(name = "StudentEntity")
@Table(name = "StudentEntity")
public class StudentEntity extends UserEntity {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_taken_lessons", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private Set<LessonEntity> takenLessons = new HashSet<>();
}
