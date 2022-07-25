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
        final ResponseTypeModel responseTypeModel = new ResponseTypeModel();
        responseTypeModel.setType(type);
        responseTypeModel.setDescription(description);
        return this.responseTypeRepository
                .findAll(this.exampleOf(responseTypeModel), pageable)
                .map(this.responseTypeMapper::mapEntityToModel);
    }

    @Override
    public Optional<ResponseTypeModel> retrieveOne(SentenceType type) {
        if (type != null) {
            final ResponseTypeModel responseTypeModel = new ResponseTypeModel();
            responseTypeModel.setType(type);
            return this.responseTypeRepository
                    .findOne(this.exampleOf(responseTypeModel))
                    .map(this.responseTypeMapper::mapEntityToModel);
        }
        return Optional.empty();
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
        final SentenceType sentenceType = SentenceType.classify(message);
        return this.retrieveOne(sentenceType)
                .orElseThrow(() -> new NotFoundException("No response type found with type=" + sentenceType));
    }

    private Example<ResponseType> exampleOf(ResponseTypeModel responseTypeModel) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase();
        if (responseTypeModel.getDescription() != null) {
            exampleMatcher = exampleMatcher.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        }
        return Example.of(this.responseTypeMapper.mapModelToEntity(responseTypeModel), exampleMatcher);
    }
}
