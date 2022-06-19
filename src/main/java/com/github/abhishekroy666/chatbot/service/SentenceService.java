package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.model.SentenceModel;

import java.util.List;

/**
 * @author Abhishek Roy
 */
public interface SentenceService {

    void create(SentenceModel response);

    List<SentenceModel> retrieve(SentenceType sentenceType);

    void update(SentenceModel sentenceModel) throws NotFoundException;

    void delete(Integer id);
}
