package com.github.abhishekroy666.chatbot.resource.web;

import com.github.abhishekroy666.chatbot.model.Message;
import com.github.abhishekroy666.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

/**
 * @author Abhishek Roy
 */
@RestController
@CrossOrigin
public class ChatWebController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/chat/web")
    public ModelAndView chat(@ModelAttribute("message") Message message) {
        return new ModelAndView("chat-ui.html", Collections.singletonMap("message", this.chatService.chat(message)));
    }
}
