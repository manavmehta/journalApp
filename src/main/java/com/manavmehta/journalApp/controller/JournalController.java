package com.manavmehta.journalApp.controller;

import com.manavmehta.journalApp.entity.JournalEntry;
import com.manavmehta.journalApp.service.JournalEntryService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        var all = service.findAll();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> journalEntry = service.findById(id);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "")
    public ResponseEntity<HttpStatus> addEntry(@RequestBody JournalEntry entry) {
        try {
            entry.setEntryDate(Instant.now());
            service.saveEntry(entry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<JournalEntry> updateEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        var entryOptional = service.findById(id);
        if (entryOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var entry = entryOptional.get();
        if (newEntry != null && newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
            entry.setTitle(newEntry.getTitle());
        }
        if (newEntry != null && newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
            entry.setContent(newEntry.getContent());
        }
        service.saveEntry(entry);
        return new ResponseEntity<>(entry, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEntryById(@PathVariable ObjectId id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error deleting entry ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}