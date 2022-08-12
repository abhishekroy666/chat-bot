package com.github.abhishekroy666.chatbot.resource.api;

import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abhishek Roy
 */
@RestController
@CrossOrigin
@RequestMapping("/api/chat")
public class ApiChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> chatApi(@RequestBody Message message) {
        return ResponseEntity.ok(this.chatService.chat(message));
    }
}
