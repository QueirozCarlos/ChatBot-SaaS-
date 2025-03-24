package com.sistema.chatbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chatbot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;
    private String response;

    // Methods for handling requests
    public String generateResponse(String input) {
        // Logic to process message
        return "Generated response for: " + input;
    }
}
