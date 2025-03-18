package com.manavmehta.journalApp.controller;

import lombok.Data;
import lombok.Getter;

@Data
public class Response {
    private final int code;
    private final String message;
}