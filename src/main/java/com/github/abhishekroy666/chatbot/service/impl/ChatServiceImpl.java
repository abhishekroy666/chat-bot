package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.enums.MessageType;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.service.ChatService;
import com.github.abhishekroy666.chatbot.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ResponseService responseService;

    private static final Map<MessageType, Integer> lastUsedIdxMap = new HashMap<>();

    @Override
    public final Message chat(Message message) {
        final MessageType messageType = MessageType.of(message);
        final StringBuilder stringBuilder = new StringBuilder();
        if (messageType != null) {
            final List<Response> responses = this.responseService.getResponses(messageType);
            Optional<Response> response = Optional.empty();
            if (!responses.isEmpty()) {
                Collections.shuffle(responses);
                response = responses.stream().findAny();
                if (lastUsedIdxMap.containsKey(messageType)) {
                    final int lastIdx = lastUsedIdxMap.get(messageType);
                    while (response.isPresent() && response.get().getId() == lastIdx) {
                        Collections.shuffle(responses);
                        response = responses.stream().findAny();
                    }
                }
            }
            response.ifPresent(r -> {
                stringBuilder.append(r.getText());
                lastUsedIdxMap.put(messageType, r.getId());
            });
        }
        return Message
                .builder()
                .name(message.getName())
                .text(message.getText())
                .response(stringBuilder.toString())
                .build();
    }
}
