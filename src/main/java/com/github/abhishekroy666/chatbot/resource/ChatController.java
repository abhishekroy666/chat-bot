package com.github.abhishekroy666.chatbot.resource;

import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

/**
 * @author Abhishek Roy
 */
@RestController
@CrossOrigin
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("")
    public ModelAndView chatInit() {
        return this.chat(Message.builder().name("").text("").response("").build());
    }

    @PostMapping("")
    public ModelAndView chat(@ModelAttribute("message") Message message) {
        return new ModelAndView("chat-web.html", Collections.singletonMap("message", this.chatService.chat(message)));
    }

    @PostMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> chatApi(@RequestBody Message message) {
        return ResponseEntity.ok(this.chatService.chat(message));
    }
}
