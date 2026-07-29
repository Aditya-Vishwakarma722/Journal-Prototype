package com.firstLearning.journalPrototype.repository;

import com.firstLearning.journalPrototype.entity.JournalEntry;
import com.firstLearning.journalPrototype.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    void deleteByUserName(String userName);
}
