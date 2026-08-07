package com.firstLearning.journalPrototype.cache;

import com.firstLearning.journalPrototype.entity.ConfigJournalEntity;
import com.firstLearning.journalPrototype.repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalEntity> list = configJournalRepository.findAll();
        for(ConfigJournalEntity configJournalEntity : list){
            APP_CACHE.put(configJournalEntity.getKey(),configJournalEntity.getValue());
        }
    }
}
