package com.manavmehta.journalApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collation = "JournalEntries")
public class JournalEntry {
    @Id
    private String id;
    private String title;
    private String content;
    private Instant entryDate;

    JournalEntry(String id, String title, String content, Instant entryDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.entryDate = entryDate;
    }
}
