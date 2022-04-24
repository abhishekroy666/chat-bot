package com.github.abhishekroy666.chatbot.resource.api;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.enums.MessageType;
import com.github.abhishekroy666.chatbot.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> createResponse() {
        final Response response = this.responseService.createResponse(null);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getResponses(@RequestParam(required = false) MessageType messageType) {
        return ResponseEntity.ok(this.responseService.getResponses(messageType));
    }
}
