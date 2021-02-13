package com.github.abhishekroy666.chatbot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HealthController {

    @GetMapping(value = "/health")
    public ResponseEntity<?> health(){
        return ResponseEntity.ok().build();
    }

}
