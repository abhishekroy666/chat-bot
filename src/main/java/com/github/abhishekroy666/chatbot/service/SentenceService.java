package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.enums.MessageType;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.model.SentenceModel;

import java.util.List;

/**
 * @author Abhishek Roy
 */
public interface SentenceService {

    void create(SentenceModel response);

    List<SentenceModel> retrieve(MessageType messageType);

    void update(SentenceModel sentenceModel) throws NotFoundException;

    void delete(Integer id);
}
