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

import java.util.Optional;

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
        final Example<Response> example = this.exampleOf(responseModel);
        this.responseRepository.findOne(example)
                .ifPresent(response -> {
                    throw new ConflictException("Sentence already exists.");
                });
        this.responseRepository.save(this.responseMapper.mapModelToEntity(responseModel));
    }

    @Override
    public Page<ResponseModel> retrieve(SentenceType sentenceType, String text, Pageable pageable) {
        final ResponseModel responseModel = new ResponseModel();
        responseModel.setText(text);
        if (sentenceType != null) {
            this.responseTypeService.retrieveOne(sentenceType)
                    .ifPresent(responseModel::setResponseType);
        }
        return this.responseRepository
                .findAll(this.exampleOf(responseModel), pageable)
                .map(this.responseMapper::mapEntityToModel);
    }

    @Override
    public void update(ResponseModel responseModel) throws NotFoundException {
        Optional<Response> sentence = this.responseRepository.findById(responseModel.getId());
        if (!sentence.isPresent()) {
            throw new NotFoundException("No sentence found with id=" + responseModel.getId());
        }
        sentence.ifPresent(s -> {
            s.setText(responseModel.getText());
            this.responseRepository.save(s);
        });
    }

    @Override
    public void delete(Integer id) {
        Optional<Response> sentence = this.responseRepository.findById(id);
        if (!sentence.isPresent()) {
            throw new NotFoundException("No sentence found with id=" + id);
        }
        sentence.ifPresent(this.responseRepository::delete);
    }

    private Example<Response> exampleOf(ResponseModel responseModel) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase();
        if (responseModel.getText() != null) {
            exampleMatcher = exampleMatcher.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        }
        return Example.of(this.responseMapper.mapModelToEntity(responseModel), exampleMatcher);
    }
}
