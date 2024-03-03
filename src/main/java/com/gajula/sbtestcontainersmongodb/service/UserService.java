package com.gajula.sbtestcontainersmongodb.service;

import com.gajula.sbtestcontainersmongodb.model.User;
import com.gajula.sbtestcontainersmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public User createUser(User user) {
        return userRepository.insert(user);
    }

    public Optional<User> readUserById(String id) {
        return userRepository.findById(id);
    }

    public List<User> readAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
