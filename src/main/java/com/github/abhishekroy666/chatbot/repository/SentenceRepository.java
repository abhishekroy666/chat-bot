package com.github.abhishekroy666.chatbot.repository;

import com.github.abhishekroy666.chatbot.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Abhishek Roy
 */
public interface SentenceRepository extends JpaRepository<Sentence, Integer> {

}
