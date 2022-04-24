package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.enums.MessageType;
import com.github.abhishekroy666.chatbot.repository.ResponseRepository;
import com.github.abhishekroy666.chatbot.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Abhishek Roy
 */
@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Override
    public final Response createResponse(Response response) {
        response = new Response();
        response.setMessageType(MessageType.ANONYMOUS);
        response.setText("some random text");
        return this.responseRepository.save(response);
    }

    @Override
    public List<Response> getResponses(MessageType messageType) {
        if (messageType != null) {
            return this.responseRepository.findByMessageType(messageType);
        }
        return this.responseRepository.findAll();
    }
}
