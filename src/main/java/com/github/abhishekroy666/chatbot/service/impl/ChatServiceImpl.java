package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
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

    private static final Map<SentenceType, Integer> lastUsedIdxMap = new HashMap<>();

    @Autowired
    private SentenceService sentenceService;

    @Override
    public final Message chat(Message message) {
        final StringBuilder response = new StringBuilder();
        this.createResponse(message)
                .ifPresent(response::append);
        return Message
                .builder()
                .name(message.getName())
                .text(message.getText())
                .response(response.toString())
                .build();
    }

    private Optional<String> createResponse(Message message) {
        final SentenceType sentenceType = MessageResponseSentenceTypeClassifier.classify(message);
        final boolean prefixable = !sentenceType.equals(SentenceType.ANONYMOUS);
        return this.randomize(this.sentenceService.retrieve(sentenceType))
                .map(sentence -> {
                    if (prefixable) {
                        Optional<SentenceModel> prefix = this.randomize(this.sentenceService.retrieve(SentenceType.PREFIX));
                        if (prefix.isPresent()) {
                            return applyPrefix(sentence.getText(), prefix.get().getText(), message);
                        }
                    }
                    return sentence.getText();
                });
    }

    private Optional<SentenceModel> randomize(List<SentenceModel> sentences) {
        Optional<SentenceModel> sentenceModel = Optional.empty();
        if (sentences != null && !sentences.isEmpty()) {
            Collections.shuffle(sentences);
            sentenceModel = sentences.stream().findAny();
            if (sentenceModel.isPresent()) {
                final SentenceType sentenceType = sentenceModel.get().getSentenceType();
                if (lastUsedIdxMap.containsKey(sentenceType)) {
                    final int lastIdx = lastUsedIdxMap.get(sentenceType);
                    while (sentenceModel.isPresent() && sentenceModel.get().getId() == lastIdx) {
                        Collections.shuffle(sentences);
                        sentenceModel = sentences.stream().findAny();
                    }
                }
            }
        }
        sentenceModel.ifPresent(sentence -> lastUsedIdxMap.put(sentence.getSentenceType(), sentence.getId()));
        return sentenceModel;
    }

    private String applyPrefix(String text, String prefix, Message message) {
        System.out.println("prefix=" + prefix + ", message=" + message);
        // TODO
        return text;
    }

    private static class MessageResponseSentenceTypeClassifier {
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
}
