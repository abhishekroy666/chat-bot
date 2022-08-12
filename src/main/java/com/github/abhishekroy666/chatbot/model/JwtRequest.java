package com.github.abhishekroy666.chatbot.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Abhishek Roy
 */
@Data
public class JwtRequest implements Serializable {

    private String username;

    private String password;

}
