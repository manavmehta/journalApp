package com.manavmehta.journalApp.controller;

import com.manavmehta.journalApp.entity.JournalEntry;
import com.manavmehta.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    JournalEntryService service;

    @GetMapping()
    public List<JournalEntry> getAllEntries() {
        return service.getAllEntries();
    }

    @GetMapping("{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return null;
    }

    @PostMapping(value = "")
    public boolean addEntry(@RequestBody JournalEntry entry) {
        entry.setEntryDate(Instant.now());
        service.saveEntry(entry);
        return true;
    }

    @PutMapping(value = "{id}")
    public boolean updateEntryById(@PathVariable Long id, @RequestBody JournalEntry entry) {
        if (entry.getEntryDate() == null){
            entry.setEntryDate(Instant.now());
        }
//        this.entries.put(id, entry);
        return true;
    }

    @DeleteMapping("{id}")
    public JournalEntry deleteEntryById(@PathVariable Long id) {
//        return this.entries.remove(id);
        return null;

    }
}