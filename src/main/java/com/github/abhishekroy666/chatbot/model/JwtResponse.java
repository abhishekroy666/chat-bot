package com.github.abhishekroy666.chatbot.model;

import java.io.Serializable;

/**
 * @author Abhishek Roy
 */
public class JwtResponse implements Serializable {

    private final String user;

    private final String token;

    public JwtResponse(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return this.user;
    }

    public String getToken() {
        return this.token;
    }
}
