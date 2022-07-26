package com.github.abhishekroy666.chatbot.enums;

import com.github.abhishekroy666.chatbot.model.Message;

public enum SentenceType {
    PREFIX,
    STATEMENT,
    QUESTION,
    BLANK,
    ANONYMOUS;

    public static SentenceType classify(Message message) {
        SentenceType sentenceType;
        if (message.getName() == null || message.getName().isEmpty()) {
            sentenceType = SentenceType.ANONYMOUS;
        } else if (message.getText() == null || message.getText().length() == 0 || message.getText().equalsIgnoreCase("?")) {
            sentenceType = SentenceType.BLANK;
        } else if (message.getText().endsWith("?")) {
            sentenceType = SentenceType.QUESTION;
        } else {
            sentenceType = SentenceType.STATEMENT;
        }
        return sentenceType;
    }
}
