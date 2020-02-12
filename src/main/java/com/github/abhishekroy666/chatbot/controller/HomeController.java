package com.github.abhishekroy666.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

    @GetMapping(value = "/dummy")
    public ResponseEntity<?> dummy(){
        return ResponseEntity.ok("Welcome to chat-bot service!!");
    }

}
