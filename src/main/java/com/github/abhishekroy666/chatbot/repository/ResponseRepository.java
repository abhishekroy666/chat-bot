package com.github.abhishekroy666.chatbot.repository;

import com.github.abhishekroy666.chatbot.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Abhishek Roy
 */
public interface ResponseRepository extends JpaRepository<Response, Integer> {

}
