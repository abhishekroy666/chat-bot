package com.github.abhishekroy666.chatbot.enums;

import com.github.abhishekroy666.chatbot.model.Message;

public enum MessageType {
    STATEMENT,
    QUESTION,
    BLANK,
    ANONYMOUS;

    public static MessageType of(Message message) {
        MessageType messageType;
        if (message.getName() == null || message.getName().isEmpty()) {
            messageType = ANONYMOUS;
        } else if (message.getText() == null || message.getText().length() == 0 || message.getText().equalsIgnoreCase("?")) {
            messageType = BLANK;
        } else if (message.getText().endsWith("?")) {
            messageType = QUESTION;
        } else {
            messageType = STATEMENT;
        }
        return messageType;
    }
}
