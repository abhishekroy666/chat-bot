package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.model.ResponseTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Abhishek Roy
 */
public interface ResponseTypeService<T> {

    void create(ResponseTypeModel responseType);

    Page<ResponseTypeModel> retrieve(SentenceType type, String description, Pageable pageable);

    Optional<ResponseTypeModel> retrieveOne(SentenceType type);

    void update(ResponseTypeModel responseType) throws NotFoundException;

    void delete(Integer id);

    ResponseTypeModel classify(T message);
}
