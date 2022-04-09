package com.github.abhishekroy666.chatbot.model;

/**
 * @author Abhishek Roy
 */
public class ChatRequest {

    private String message;

    public ChatRequest() {
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String message) {
        this.message = message;
    }
}
