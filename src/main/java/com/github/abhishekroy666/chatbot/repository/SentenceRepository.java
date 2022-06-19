package com.github.abhishekroy666.chatbot.repository;

import com.github.abhishekroy666.chatbot.entity.Sentence;
import com.github.abhishekroy666.chatbot.enums.SentenceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Abhishek Roy
 */
public interface SentenceRepository extends JpaRepository<Sentence, Integer> {

    List<Sentence> findBySentenceType(SentenceType sentenceType);

    Optional<Sentence> findBySentenceTypeAndTextIgnoreCase(SentenceType sentenceType, String text);
}
