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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase(true);
        Example<Sentence> example = Example.of(this.sentenceMapper.mapModelToEntity(sentenceModel), exampleMatcher);
        Optional<Sentence> sentence = this.sentenceRepository.findOne(example);
        if (sentence.isPresent()) {
            throw new ConflictException("Sentence already exists.");
        }
        sentenceModel.setId(null);
        this.sentenceRepository.save(this.sentenceMapper.mapModelToEntity(sentenceModel));
    }

    @Override
    public Page<SentenceModel> retrieve(SentenceType sentenceType, Pageable pageable) {
        final Sentence sentence = new Sentence();
        if (sentenceType != null) {
            sentence.setSentenceType(sentenceType);
        }
        return this.sentenceRepository
                .findAll(Example.of(sentence), pageable)
                .map(this.sentenceMapper::mapEntityToModel);
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
