package com.github.abhishekroy666.chatbot.service.impl;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.exception.ConflictException;
import com.github.abhishekroy666.chatbot.exception.NotFoundException;
import com.github.abhishekroy666.chatbot.mapper.ResponseMapper;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import com.github.abhishekroy666.chatbot.repository.ResponseRepository;
import com.github.abhishekroy666.chatbot.service.ResponseService;
import com.github.abhishekroy666.chatbot.service.ResponseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Abhishek Roy
 */
@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ResponseTypeService<Message> responseTypeService;

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public final void create(ResponseModel responseModel) {
        this.responseRepository.findOne(this.exampleOf(responseModel))
                .ifPresent(response -> {
                    throw new ConflictException("Response already exists");
                });
        this.responseRepository.save(this.responseMapper.mapModelToEntity(responseModel));
    }

    @Override
    public Page<ResponseModel> retrieve(SentenceType sentenceType, String text, Pageable pageable) {
        final ResponseModel responseModel = new ResponseModel();
        responseModel.setText(text);
        this.responseTypeService.retrieveOne(sentenceType)
                .ifPresent(responseModel::setResponseType);
        return this.responseRepository
                .findAll(this.exampleOf(responseModel), pageable)
                .map(this.responseMapper::mapEntityToModel);
    }

    @Override
    public void update(ResponseModel responseModel) throws NotFoundException {
        this.responseRepository.findById(responseModel.getId())
                .map(sentence -> {
                    this.responseMapper.update(sentence, responseModel);
                    return this.responseRepository.save(sentence);
                })
                .orElseThrow(() -> new NotFoundException("No response found with id=" + responseModel.getId()));
    }

    @Override
    public void delete(Integer id) {
        this.responseRepository.findById(id)
                .map(response -> {
                    this.responseRepository.delete(response);
                    return response;
                })
                .orElseThrow(() -> new NotFoundException("No response found with id=" + id));
    }

    private Example<Response> exampleOf(ResponseModel responseModel) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase();
        if (responseModel.getText() != null) {
            exampleMatcher = exampleMatcher.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        }
        return Example.of(this.responseMapper.mapModelToEntity(responseModel), exampleMatcher);
    }
}
