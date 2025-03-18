package com.manavmehta.journalApp.service;

import com.manavmehta.journalApp.entity.JournalEntry;
import com.manavmehta.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository; // Spring will generate an instance of this repo on runtime

    public void saveEntry(JournalEntry entry){
        repository.save(entry);
    }

    public List<JournalEntry> getAllEntries(){
        return repository.findAll();
    }
}
