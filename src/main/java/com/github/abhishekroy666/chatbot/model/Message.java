package com.github.abhishekroy666.chatbot.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Abhishek Roy
 */
@Data
@Builder
public class Message {

    private String name;

    private String text;

    private String response;
}
