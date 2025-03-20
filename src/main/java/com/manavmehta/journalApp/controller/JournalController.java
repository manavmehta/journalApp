package com.manavmehta.journalApp.controller;

import com.manavmehta.journalApp.entity.JournalEntry;
import com.manavmehta.journalApp.service.JournalEntryService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    JournalEntryService service;

    @GetMapping()
    public List<JournalEntry> getAllEntries() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Optional<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        return service.findById(id);
    }

    @PostMapping(value = "")
    public boolean addEntry(@RequestBody JournalEntry entry) {
        entry.setEntryDate(Instant.now());
        service.saveEntry(entry);
        return true;
    }

    @PutMapping(value = "{id}")
    public boolean updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        var entry = service.findById(id).get();
        if (newEntry != null && newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
            entry.setTitle(newEntry.getTitle());
        }
        if (newEntry != null && newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
            entry.setContent(newEntry.getContent());
        }
        service.saveEntry(entry);
        return true;
    }

    @DeleteMapping("{id}")
    public boolean deleteEntryById(@PathVariable ObjectId id) {
        try {
            service.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting entry ", e);
            return false;
        }
    }
}