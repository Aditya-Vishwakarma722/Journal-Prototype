package service;

import com.firstLearning.journalPrototype.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.firstLearning.journalPrototype.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.firstLearning.journalPrototype.JournalPrototypeApplication.class)
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @ParameterizedTest
    @CsvSource({"Aditya", "Rishabh", "Aman"})
    public void testFindByName(String Name){
        assertNotNull(userRepository.findByUserName(Name), "Failed for "+Name);
    }

}
