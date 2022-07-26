package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Abhishek Roy
 */
public interface ResponseService {

    void create(ResponseModel response);

    Page<ResponseModel> retrieve(SentenceType type, String text, Pageable pageable);

    void update(ResponseModel responseModel) throws NotFoundException;

    void delete(Integer id);
}
