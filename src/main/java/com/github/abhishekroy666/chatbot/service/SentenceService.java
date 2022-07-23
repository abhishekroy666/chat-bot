package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.model.SentenceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Abhishek Roy
 */
public interface SentenceService {

    void create(SentenceModel response);

    Page<SentenceModel> retrieve(SentenceType sentenceType, Pageable pageable);

    void update(SentenceModel sentenceModel) throws NotFoundException;

    void delete(Integer id);
}
