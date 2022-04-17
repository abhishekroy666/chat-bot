package com.github.abhishekroy666.chatbot.enums;

public enum ChatType {
    STATEMENT("I HEARD YOU!"),
    QUESTION("SO, YOU ARE TALKING TO ME."),
    BLANK("CONTINUE, I’M LISTENING."),
    NO_NAME("VERY INTERESTING CONVERSATION."),
    RESPONSE_5("TELL ME MORE..."),
    RESPONSE_7("ALRIGHT!"),
    RESPONSE_9("OKAY.");

    private final String text;

    ChatType(String text) {
        this.text = text;
    }

    public final String getText() {
        return this.text;
    }
}
