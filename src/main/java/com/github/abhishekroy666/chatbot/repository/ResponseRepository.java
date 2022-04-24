package com.github.abhishekroy666.chatbot.repository;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.enums.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Abhishek Roy
 */
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    List<Response> findByMessageType(MessageType messageType);
}
