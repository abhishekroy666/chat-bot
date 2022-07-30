package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import com.github.abhishekroy666.chatbot.model.ResponseTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Abhishek Roy
 */
public interface ResponseService {

    void create(ResponseModel response);

    Page<ResponseModel> retrieve(ResponseTypeModel type, String text, Pageable pageable);

    void update(ResponseModel responseModel) throws NotFoundException;

    void delete(Integer id);
}
