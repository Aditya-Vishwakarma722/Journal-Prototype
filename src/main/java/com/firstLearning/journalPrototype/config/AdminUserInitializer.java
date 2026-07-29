package com.firstLearning.journalPrototype.config;

import com.firstLearning.journalPrototype.entity.User;
import com.firstLearning.journalPrototype.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User existingAdmin = userService.findByUserName("Psyko");
        if (existingAdmin == null) {
            User admin = new User();
            admin.setUserName("Psyko");
            admin.setPassword("psyko7");
            userService.saveAdmin(admin);
        }
    }
}
