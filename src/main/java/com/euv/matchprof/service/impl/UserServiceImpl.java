package com.euv.matchprof.service.impl;

import com.euv.matchprof.model.entity.user.UserEntity;
import com.euv.matchprof.model.exception.EntityAlreadySavedException;
import com.euv.matchprof.model.exception.EntityNotFoundException;
import com.euv.matchprof.repository.UserRepository;
import com.euv.matchprof.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public UserEntity findById(Long id) throws EntityNotFoundException {
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("User is not found: " + id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity user) throws EntityAlreadySavedException {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new EntityAlreadySavedException("User is already saved before: " + user.getId());
        }
        return userRepository.save(user);
    }

    @Override
    public UserEntity update(UserEntity user) throws EntityNotFoundException {
        try {
            UserEntity savedUser = this.findById(user.getId());
            savedUser.setId(user.getId());
            savedUser.setName(user.getName());
            savedUser.setSurname(user.getSurname());
            savedUser.setEmail(user.getEmail());
            savedUser.setPassword(user.getPassword());
            savedUser.setPhoneNumber(user.getPhoneNumber());
            savedUser.setEnabled(savedUser.isEnabled());
            return this.userRepository.save(user);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        try {
            UserEntity savedUser = this.findById(id);
            this.userRepository.delete(savedUser);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public boolean hasAnyRecord() {
        return userRepository.existsByIdIsNotNull();
    }
}
