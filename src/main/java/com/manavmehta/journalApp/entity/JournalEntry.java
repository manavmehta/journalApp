package com.manavmehta.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Instant entryDate;

    JournalEntry(ObjectId id, String title, String content, Instant entryDate){
        this.id = id;
        this.title = title;
        this.content = content;
        this.entryDate = entryDate;
    }
}
