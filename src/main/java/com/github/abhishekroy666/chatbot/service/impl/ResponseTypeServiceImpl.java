package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.entity.ResponseType;
import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.ConflictException;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.mapper.ResponseTypeMapper;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.model.ResponseTypeModel;
import com.github.abhishekroy666.chatbot.repository.ResponseTypeRepository;
import com.github.abhishekroy666.chatbot.service.ResponseTypeService;
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
public class ResponseTypeServiceImpl implements ResponseTypeService<Message> {

    @Autowired
    private ResponseTypeRepository responseTypeRepository;

    @Autowired
    private ResponseTypeMapper responseTypeMapper;

    @Override
    public void create(ResponseTypeModel responseTypeModel) {
        final ResponseType responseType = new ResponseType();
        responseType.setType(responseTypeModel.getType());
        final ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase(true);
        final Example<ResponseType> example = Example.of(responseType, exampleMatcher);
        this.responseTypeRepository.findOne(example)
                .ifPresent(existing -> {
                    throw new ConflictException("Response type already exists.");
                });
        this.responseTypeRepository.save(this.responseTypeMapper.mapModelToEntity(responseTypeModel));
    }

    @Override
    public Page<ResponseTypeModel> retrieve(SentenceType type, String description, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        final ResponseType responseType = new ResponseType();
        responseType.setType(type);
        responseType.setDescription(description);
        return this.responseTypeRepository
                .findAll(Example.of(responseType, matcher), pageable)
                .map(this.responseTypeMapper::mapEntityToModel);
    }

    @Override
    public Optional<ResponseTypeModel> retrieveOne(SentenceType type) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        final ResponseType responseType = new ResponseType();
        responseType.setType(type);
        return this.responseTypeRepository
                .findOne(Example.of(responseType, matcher))
                .map(this.responseTypeMapper::mapEntityToModel);
    }

    @Override
    public void update(ResponseTypeModel responseTypeModel) throws NotFoundException {
        this.responseTypeRepository.findById(responseTypeModel.getId())
                .map(responseType -> {
                    this.responseTypeMapper.update(responseType, responseTypeModel);
                    return this.responseTypeRepository.save(responseType);
                })
                .orElseThrow(() -> new NotFoundException("No response type found with id=" + responseTypeModel.getId()));
    }

    @Override
    public void delete(Integer id) {
        this.responseTypeRepository.findById(id)
                .map(responseType -> {
                    this.responseTypeRepository.delete(responseType);
                    return responseType;
                })
                .orElseThrow(() -> new NotFoundException("No response type found with id=" + id));
    }

    @Override
    public ResponseTypeModel classify(Message message) {
        SentenceType sentenceType;
        if (message.getName() == null || message.getName().isEmpty()) {
            sentenceType = SentenceType.ANONYMOUS;
        } else if (message.getText() == null || message.getText().length() == 0 || message.getText().equalsIgnoreCase("?")) {
            sentenceType = SentenceType.BLANK;
        } else if (message.getText().endsWith("?")) {
            sentenceType = SentenceType.QUESTION;
        } else {
            sentenceType = SentenceType.STATEMENT;
        }
        return this.retrieveOne(sentenceType)
                .orElseThrow(() -> new NotFoundException("No response type found with type=" + sentenceType));
    }
}
