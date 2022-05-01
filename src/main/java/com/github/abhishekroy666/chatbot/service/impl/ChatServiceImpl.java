package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.enums.MessageType;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.model.SentenceModel;
import com.github.abhishekroy666.chatbot.service.ChatService;
import com.github.abhishekroy666.chatbot.service.SentenceService;
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
    private SentenceService sentenceService;

    private static final Map<MessageType, Integer> lastUsedIdxMap = new HashMap<>();

    @Override
    public final Message chat(Message message) {
        final MessageType messageType = MessageType.of(message);
        final StringBuilder stringBuilder = new StringBuilder();
        if (messageType != null) {
            final List<SentenceModel> sentences = this.sentenceService.retrieve(messageType);
            Optional<SentenceModel> sentence = Optional.empty();
            if (!sentences.isEmpty()) {
                Collections.shuffle(sentences);
                sentence = sentences.stream().findAny();
                if (lastUsedIdxMap.containsKey(messageType)) {
                    final int lastIdx = lastUsedIdxMap.get(messageType);
                    while (sentence.isPresent() && sentence.get().getId() == lastIdx) {
                        Collections.shuffle(sentences);
                        sentence = sentences.stream().findAny();
                    }
                }
            }
            sentence.ifPresent(s -> {
                stringBuilder.append(s.getText());
                lastUsedIdxMap.put(messageType, s.getId());
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
