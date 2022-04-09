package com.github.abhishekroy666.chatbot.model;

/**
 * @author Abhishek Roy
 */
public class ChatResponse {

    private final String message;

    private final String response;

    public ChatResponse(String message, String response) {
        this.message = message;
        this.response = response;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getResponse() {
        return this.response;
    }

}
