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

    private static final Random RANDOM = new Random();

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
        final ResponseTypeModel responseType = this.responseTypeService.classify(message);
        return randomize(this.responseService.retrieve(responseType, null, Pageable.unpaged()).toList())
                .map(sentence ->
                        this.responseTypeService.retrieveOne(SentenceType.PREFIX)
                                .map(prefix ->
                                        randomize(this.responseService.retrieve(prefix, null, Pageable.unpaged()).toList())
                                                .map(prefixModel -> applyPrefix(sentence.getText(), prefixModel.getText(), responseType, message))
                                                .orElse("")
                                )
                                .orElse(sentence.getText()));
    }

    private static Optional<ResponseModel> randomize(List<ResponseModel> sentences) {
        Optional<ResponseModel> sentenceModel = Optional.empty();
        if (sentences != null && !sentences.isEmpty()) {
            sentenceModel = getRandomFromListOf(sentences);
            if (sentenceModel.isPresent()) {
                final ResponseTypeModel responseTypeModel = sentenceModel.get().getResponseType();
                if (LAST_USED_RESPONSE_MAP.containsKey(responseTypeModel)) {
                    final int prevRespId = LAST_USED_RESPONSE_MAP.get(responseTypeModel);
                    while (sentenceModel.isPresent() && sentenceModel.get().getId() == prevRespId) {
                        sentenceModel = getRandomFromListOf(sentences);
                    }
                }
            }
        }
        sentenceModel.ifPresent(sentence -> LAST_USED_RESPONSE_MAP.put(sentence.getResponseType(), sentence.getId()));
        return sentenceModel;
    }

    private static <T> Optional<T> getRandomFromListOf(List<T> list) {
        if (list != null && !list.isEmpty()) {
            final int idx = RANDOM.nextInt(list.size());
            return Optional.of(list.get(idx));
        }
        return Optional.empty();
    }

    private static String applyPrefix(String text, String prefix, ResponseTypeModel responseType, Message message) {
        if (RANDOM.nextBoolean()) {
            if (responseType.getType() != SentenceType.ANONYMOUS && RANDOM.nextBoolean()) {
                prefix += " " + getRandomToken(message.getName());
            }
            if (prefix.length() < text.length()) {
                text = prefix + ", " + makePrefixable(text);
            }
        }
        return text;
    }

    private static String getRandomToken(String string) {
        if (string != null && !string.isEmpty() && RANDOM.nextBoolean()) {
            final String[] tokens = string.split(" ");
            int idx = RANDOM.nextInt(tokens.length);
            String token = tokens[idx];
            return (token != null) ? token : string;
        }
        return string;
    }

    private static String makePrefixable(String text) {
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
