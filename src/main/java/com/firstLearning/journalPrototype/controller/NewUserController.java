package com.firstLearning.journalPrototype.controller;

import com.firstLearning.journalPrototype.entity.User;
import com.firstLearning.journalPrototype.service.NewUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class NewUserController {

    @Autowired
    private NewUserService newUserService;

    @PostMapping
    public ResponseEntity<?> signupUser(@RequestBody User user) {
        boolean created = newUserService.addNewUser(user);
        if (created) {
            return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to register user. Username may already exist or required fields are missing.", HttpStatus.BAD_REQUEST);
    }
}

