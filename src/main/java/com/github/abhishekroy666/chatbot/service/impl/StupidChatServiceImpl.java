package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.enums.ResponseType;
import com.github.abhishekroy666.chatbot.model.ChatRequest;
import com.github.abhishekroy666.chatbot.model.ChatResponse;
import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Random;

@Service
public class StupidChatServiceImpl implements ChatService {

    private static ResponseType PREV_RESPONSE_TYPE;

    private static final String DEFAULT_RESPONSE_MESSAGE = "WHAT DO YOU HAVE IN MIND?";

    @Override
    public ChatResponse getGenericResponse(ChatRequest chatRequest) {
        String requestMessage = chatRequest.getMessage();
        String responseMessage;
        if (requestMessage == null || requestMessage.length() == 0) {
            responseMessage = DEFAULT_RESPONSE_MESSAGE;
        } else {
            ResponseType responseType = null;
            final Random random = new Random();
            while (responseType == null || PREV_RESPONSE_TYPE == null || responseType.equals(PREV_RESPONSE_TYPE)) {
                responseType = ResponseType.values()[random.nextInt(ResponseType.values().length)];
                if (ObjectUtils.isEmpty(PREV_RESPONSE_TYPE)) {
                    PREV_RESPONSE_TYPE = responseType;
                }
            }
            PREV_RESPONSE_TYPE = responseType;
            responseMessage = responseType.getText();
        }
        return new ChatResponse(requestMessage, responseMessage);
    }
}
