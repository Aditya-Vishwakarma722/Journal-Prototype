package com.firstLearning.journalPrototype.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testMailSender(){
        emailService.sendEmail("adityaarmy007@gmail.com",
                "Testing JavaMailSender",
                "I am doing the Java Mail Sender Testing to check whether I can send a mail using my" +
                        "JAVA program to any other Gmail or not, this mail is sned by a EmailServiceTest.class file in my IntelliJ");
    }
}
