package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import com.github.abhishekroy666.chatbot.model.ResponseTypeModel;
import com.github.abhishekroy666.chatbot.service.ChatService;
import com.github.abhishekroy666.chatbot.service.ResponseService;
import com.github.abhishekroy666.chatbot.service.ResponseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Map<ResponseTypeModel, Integer> LAST_USED_RESPONSE_MAP = new HashMap<>();

    private final Random random = new Random();

    @Autowired
    private ResponseService responseService;

    @Autowired
    private ResponseTypeService<Message> responseTypeService;

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
        final SentenceType sentenceType = SentenceType.classify(message);
        final boolean prefixable = !sentenceType.equals(SentenceType.ANONYMOUS);
        return this.randomize(this.responseService.retrieve(sentenceType, null, Pageable.unpaged()).toList())
                .map(sentence -> {
                    if (prefixable) {
                        Optional<ResponseModel> prefix = this.randomize(this.responseService.retrieve(SentenceType.PREFIX, null, Pageable.unpaged()).toList());
                        if (prefix.isPresent()) {
                            return applyPrefix(sentence.getText(), prefix.get().getText(), message);
                        }
                    }
                    return sentence.getText();
                });
    }

    private Optional<ResponseModel> randomize(List<ResponseModel> sentences) {
        Optional<ResponseModel> sentenceModel = Optional.empty();
        if (sentences != null && !sentences.isEmpty()) {
            sentenceModel = this.fetchRandomSentenceModel(sentences);
            if (sentenceModel.isPresent()) {
                final ResponseTypeModel responseTypeModel = sentenceModel.get().getResponseType();
                if (LAST_USED_RESPONSE_MAP.containsKey(responseTypeModel)) {
                    final int prevRespId = LAST_USED_RESPONSE_MAP.get(responseTypeModel);
                    while (sentenceModel.isPresent() && sentenceModel.get().getId() == prevRespId) {
                        sentenceModel = this.fetchRandomSentenceModel(sentences);
                    }
                }
            }
        }
        sentenceModel.ifPresent(sentence -> LAST_USED_RESPONSE_MAP.put(sentence.getResponseType(), sentence.getId()));
        return sentenceModel;
    }

    private Optional<ResponseModel> fetchRandomSentenceModel(List<ResponseModel> sentences) {
        final int idx = this.random.nextInt(sentences.size());
        return Optional.of(sentences.get(idx));
    }

    private String applyPrefix(String text, String prefix, Message message) {
        if (this.random.nextBoolean()) {
            if (this.random.nextBoolean()) {
                prefix += " " + this.randomizeName(message.getName()) + ",";
            }
            if (prefix.length() < text.length()) {
                text = prefix + " " + this.makePrefixable(text);
            }
        }
        return text;
    }

    public String randomizeName(String name) {
        if (name != null && !name.isEmpty() && this.random.nextBoolean()) {
            final String[] nameParts = name.split(" ");
            int idx = random.nextInt(nameParts.length);
            String part = nameParts[idx];
            return (part != null) ? part : name;
        }
        return name;
    }

    private String makePrefixable(String text) {
        if (text != null && !text.isEmpty()) {
            final String[] words = text.split(" ");
            if (words.length >= 1) {
                final char[] firstWord = words[0].toCharArray();
                if (firstWord.length > 1) {
                    final char[] chars = text.toCharArray();
                    char ch = chars[0];
                    ch = ("" + ch).toLowerCase().charAt(0);
                    chars[0] = ch;
                    text = new String(chars);
                }
            }
        }
        return text;
    }
}
