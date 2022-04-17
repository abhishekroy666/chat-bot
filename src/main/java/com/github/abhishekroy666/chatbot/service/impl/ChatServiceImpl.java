package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.enums.ChatType;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Random;

@Service
public class ChatServiceImpl implements ChatService {

    private static final String DEFAULT_RESPONSE_TEXT = "WHAT DO YOU HAVE IN MIND?";

    @Override
    public Message chat(Message message) {
        String text = message.getText();
        String response;
        if (message.getName() == null || message.getName().isEmpty()) {
            response = "Please say your name";
        } else if (text == null || text.length() == 0 || text.equalsIgnoreCase("?")) {
            response = DEFAULT_RESPONSE_TEXT;
        } else {
            ChatType chatType = null;
            final Random random = new Random();
            while (chatType == null || message.getChatType() == null || chatType.equals(message.getChatType())) {
                chatType = ChatType.values()[random.nextInt(ChatType.values().length)];
                if (ObjectUtils.isEmpty(message.getChatType())) {
                    message.setChatType(chatType);
                }
            }
            message.setChatType(chatType);
            response = chatType.getText();
        }
        return Message
                .builder()
                .name(message.getName())
                .chatType(message.getChatType())
                .text(text)
                .response(response)
                .build();
    }
}
