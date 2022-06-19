package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.entity.Sentence;
import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.ConflictException;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.mapper.SentenceMapper;
import com.github.abhishekroy666.chatbot.model.SentenceModel;
import com.github.abhishekroy666.chatbot.repository.SentenceRepository;
import com.github.abhishekroy666.chatbot.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Abhishek Roy
 */
@Service
public class SentenceServiceImpl implements SentenceService {

    @Autowired
    private SentenceRepository sentenceRepository;

    @Autowired
    private SentenceMapper sentenceMapper;

    @Override
    public final void create(SentenceModel sentenceModel) {
        Optional<Sentence> sentence = this.sentenceRepository.findBySentenceTypeAndTextIgnoreCase(sentenceModel.getSentenceType(), sentenceModel.getText());
        if (sentence.isPresent()) {
            throw new ConflictException("Sentence already exists.");
        }
        sentenceModel.setId(null);
        this.sentenceRepository.save(this.sentenceMapper.mapModelToEntity(sentenceModel));
    }

    @Override
    public List<SentenceModel> retrieve(SentenceType sentenceType) {
        final List<Sentence> sentences = new ArrayList<>();
        if (sentenceType != null) {
            sentences.addAll(this.sentenceRepository.findBySentenceType(sentenceType));
        } else {
            sentences.addAll(this.sentenceRepository.findAll());
        }
        return sentences
                .stream()
                .map(this.sentenceMapper::mapEntityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public void update(SentenceModel sentenceModel) throws NotFoundException {
        Optional<Sentence> sentence = this.sentenceRepository.findById(sentenceModel.getId());
        if (!sentence.isPresent()) {
            throw new NotFoundException("No sentence found with id=" + sentenceModel.getId());
        }
        sentence.ifPresent(s -> {
            s.setText(sentenceModel.getText());
            this.sentenceRepository.save(s);
        });
    }

    @Override
    public void delete(Integer id) {
        Optional<Sentence> sentence = this.sentenceRepository.findById(id);
        if (!sentence.isPresent()) {
            throw new NotFoundException("No sentence found with id=" + id);
        }
        sentence.ifPresent(this.sentenceRepository::delete);
    }
}
