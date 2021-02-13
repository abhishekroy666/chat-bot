package com.github.abhishekroy666.chatbot.controller;

import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getResponse(@RequestParam(name = "text", required = false) String text){
        return this.chatService.getGenericResponse(text);
    }
}
