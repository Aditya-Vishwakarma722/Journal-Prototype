package com.firstLearning.journalPrototype.service;

import com.firstLearning.journalPrototype.entity.User;
import com.firstLearning.journalPrototype.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class NewUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean addNewUser(User user) {
        if (user == null || user.getUserName() == null || user.getUserName().trim().isEmpty() 
                || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        }

        User existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Arrays.asList("USER"));
        }
        userRepository.save(user);
        return true;
    }
}

