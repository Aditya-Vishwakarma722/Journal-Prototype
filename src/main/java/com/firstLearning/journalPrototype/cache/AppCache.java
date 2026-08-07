package com.firstLearning.journalPrototype.cache;

import com.firstLearning.journalPrototype.entity.ConfigJournalEntity;
import com.firstLearning.journalPrototype.repository.ConfigJournalRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String, String> APP_CACHE = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalEntity> list = configJournalRepository.findAll();
        Map<String, String> tempCache = new HashMap<>();
        for (ConfigJournalEntity configJournalEntity : list) {
            String key = configJournalEntity.getKey() != null ? configJournalEntity.getKey() : configJournalEntity.getName();
            String value = configJournalEntity.getValue() != null ? configJournalEntity.getValue() : configJournalEntity.getKeyID();
            if (key != null && value != null) {
                tempCache.put(key, value);
            }
        }
        APP_CACHE.clear();
        APP_CACHE.putAll(tempCache);
    }

    public String get(String key) {
        return APP_CACHE.get(key);
    }
}

