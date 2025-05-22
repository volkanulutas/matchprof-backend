package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.user.TeacherEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.TeacherRepository;
import com.euv.matchprof.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {this.teacherRepository = teacherRepository;}

    @Override
    public TeacherEntity findById(Long id) throws EntityNotFoundException {
        Optional<TeacherEntity> optional = teacherRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Teacher is not found: " + id);
    }

    @Override
    public List<TeacherEntity> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherEntity save(TeacherEntity teacher) throws EntityAlreadySavedException {
        if (teacher.getId() != null && teacherRepository.existsById(teacher.getId())) {
            throw new EntityAlreadySavedException("Teacher is already saved before: " + teacher.getId());
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public TeacherEntity update(TeacherEntity teacher) throws EntityNotFoundException {
        try {
            TeacherEntity savedTeacher = this.findById(teacher.getId());
            savedTeacher.setId(teacher.getId());
            savedTeacher.setName(teacher.getName());
            savedTeacher.setSurname(teacher.getSurname());
            savedTeacher.setEmail(teacher.getEmail());
            savedTeacher.setPassword(teacher.getPassword());
            savedTeacher.setPhoneNumber(teacher.getPhoneNumber());
            savedTeacher.setGivenLessons(teacher.getGivenLessons());
            savedTeacher.setProfilePicture(teacher.getProfilePicture());
            savedTeacher.setBirthdate(teacher.getBirthdate());
            savedTeacher.setStudentNumber(teacher.getStudentNumber());
            savedTeacher.setEnabled(savedTeacher.isEnabled());
            savedTeacher.setChargeByHour(teacher.getChargeByHour());
            savedTeacher.setReplyTimeToRequests(teacher.getReplyTimeToRequests());
            return this.teacherRepository.save(savedTeacher);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            TeacherEntity savedTeacher = this.findById(id);
            this.teacherRepository.delete(savedTeacher);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public boolean hasAnyRecord() {
        return teacherRepository.existsByIdIsNotNull();
    }
}
