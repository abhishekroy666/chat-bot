package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.enums.MessageType;

import java.util.List;

/**
 * @author Abhishek Roy
 */
public interface ResponseService {

    Response createResponse(Response response);

    List<Response> getResponses(MessageType messageType);
}
