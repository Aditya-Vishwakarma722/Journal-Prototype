package com.firstLearning.journalPrototype.service;

import com.firstLearning.journalPrototype.entity.User;
import com.firstLearning.journalPrototype.repository.journalEntryRepository;
import com.firstLearning.journalPrototype.entity.JournalEntry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class journalEntryService {

    @Autowired
    private journalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            log.error("Error Occurred : ",e);
            log.warn("Check the code for bugs!");
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId myId, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(myId);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
        return removed;
    }

    public String deleteSimple(ObjectId myId){
        journalEntryRepository.deleteById(myId);
        return "ID Deleted Successfully!";
    }

}

