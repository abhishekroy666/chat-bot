package com.github.abhishekroy666.chatbot.service;

import com.github.abhishekroy666.chatbot.model.ChatRequest;
import com.github.abhishekroy666.chatbot.model.ChatResponse;

public interface ChatService {

    ChatResponse getGenericResponse(ChatRequest chatRequest);
}
