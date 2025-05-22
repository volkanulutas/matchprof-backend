package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.user.StudentEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.StudentRepository;
import com.euv.matchprof.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {this.studentRepository = studentRepository;}

    @Override
    public StudentEntity findById(Long id) throws EntityNotFoundException {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Student is not found: " + id);
    }

    @Override
    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity save(StudentEntity student) throws EntityAlreadySavedException {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new EntityAlreadySavedException("Student is already saved before: " + student.getId());
        }
        return studentRepository.save(student);
    }

    @Override
    public StudentEntity update(StudentEntity message) throws EntityNotFoundException {
        try {
            StudentEntity savedMessage = this.findById(message.getId());
            savedMessage.setId(message.getId());
            savedMessage.setName(message.getName());
            savedMessage.setSurname(message.getSurname());
            savedMessage.setEmail(message.getEmail());
            savedMessage.setPassword(message.getPassword());
            savedMessage.setPhoneNumber(message.getPhoneNumber());
            savedMessage.setTakenLessons(message.getTakenLessons());
            savedMessage.setProfilePicture(message.getProfilePicture());
            savedMessage.setEnabled(savedMessage.isEnabled());
            return this.studentRepository.save(savedMessage);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            StudentEntity savedMessage = this.findById(id);
            this.studentRepository.delete(savedMessage);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public boolean hasAnyRecord() {
        return studentRepository.existsByIdIsNotNull();
    }
}
