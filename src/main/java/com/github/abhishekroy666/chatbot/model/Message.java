package com.github.abhishekroy666.chatbot.model;

import com.github.abhishekroy666.chatbot.enums.ChatType;
import lombok.Builder;
import lombok.Data;

/**
 * @author Abhishek Roy
 */
@Data
@Builder
public class Message {

    private String name;

    private ChatType chatType;

    private String text;

    private String response;
}
