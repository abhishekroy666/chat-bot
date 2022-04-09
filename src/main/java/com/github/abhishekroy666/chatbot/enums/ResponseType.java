package com.github.abhishekroy666.chatbot.enums;

public enum ResponseType {
    RESPONSE_1("I HEARD YOU!"),
    RESPONSE_2("SO, YOU ARE TALKING TO ME."),
    RESPONSE_3("CONTINUE, I’M LISTENING."),
    RESPONSE_4("VERY INTERESTING CONVERSATION."),
    RESPONSE_5("TELL ME MORE..."),
    RESPONSE_7("ALRIGHT!"),
    RESPONSE_9("OKAY.");

    private final String text;

    ResponseType(String text) {
        this.text = text;
    }

    public final String getText() {
        return this.text;
    }
}
