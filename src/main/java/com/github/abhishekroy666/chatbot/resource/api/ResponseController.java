package com.github.abhishekroy666.chatbot.resource.api;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import com.github.abhishekroy666.chatbot.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Abhishek Roy
 */
@RestController
@CrossOrigin
@RequestMapping("/response")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ResponseModel responseModel) {
        this.responseService.create(responseModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> retrieve(@RequestParam(required = false) SentenceType sentenceType,
                                      @RequestParam(required = false) String text,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        final Pageable pageable = (page != null && size != null)
                ? PageRequest.of(page, size)
                : Pageable.unpaged();
        return ResponseEntity.ok(this.responseService.retrieve(sentenceType, text, pageable));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ResponseModel responseModel) {
        this.responseService.update(responseModel);
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        this.responseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
