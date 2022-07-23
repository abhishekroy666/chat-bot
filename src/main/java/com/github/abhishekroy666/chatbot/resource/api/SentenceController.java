package com.github.abhishekroy666.chatbot.resource.api;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.model.SentenceModel;
import com.github.abhishekroy666.chatbot.service.SentenceService;
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
@RequestMapping("/sentence")
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SentenceModel sentenceModel) {
        this.sentenceService.create(sentenceModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> retrieve(@RequestParam(required = false) SentenceType sentenceType,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        final Pageable pageable = (page != null && size != null)
                ? PageRequest.of(page, size)
                : Pageable.unpaged();
        return ResponseEntity.ok(this.sentenceService.retrieve(sentenceType, pageable));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid SentenceModel sentenceModel) {
        this.sentenceService.update(sentenceModel);
        return ResponseEntity.ok(sentenceModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        this.sentenceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
