package com.github.abhishekroy666.chatbot.resource.api;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.model.ResponseTypeModel;
import com.github.abhishekroy666.chatbot.service.ResponseTypeService;
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
@RequestMapping("/response/type")
public class ResponseTypeController {

    @Autowired
    private ResponseTypeService<Message> responseTypeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ResponseTypeModel responseType) {
        this.responseTypeService.create(responseType);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> retrieve(@RequestParam(required = false) SentenceType type,
                                      @RequestParam(required = false) String description,
                                      @RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        final Pageable pageable = (page != null && size != null)
                ? PageRequest.of(page, size)
                : Pageable.unpaged();
        return ResponseEntity.ok(this.responseTypeService.retrieve(type, description, pageable));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid ResponseTypeModel responseType) {
        this.responseTypeService.update(responseType);
        return ResponseEntity.ok(responseType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        this.responseTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
