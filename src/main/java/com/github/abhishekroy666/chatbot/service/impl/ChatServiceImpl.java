package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.enums.GenericResponse;
import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ChatServiceImpl implements ChatService {

    private static GenericResponse LAST_RESPONSE;

    @Override
    public String getGenericResponse(String text) {
        if(StringUtils.isEmpty(text)) {
            return "WHAT DO YOU HAVE IN MIND?";
        }
        GenericResponse response = null;
        final Random random = new Random();
        final List<GenericResponse> genericResponses = Arrays.asList(GenericResponse.values());
        while(ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(LAST_RESPONSE) || response.equals(LAST_RESPONSE)) {
            response = genericResponses.get(random.nextInt(genericResponses.size()));
            if(ObjectUtils.isEmpty(LAST_RESPONSE)) {
                LAST_RESPONSE = response;
            }
        }
        LAST_RESPONSE = response;
        return response.getResponse();
    }
}
