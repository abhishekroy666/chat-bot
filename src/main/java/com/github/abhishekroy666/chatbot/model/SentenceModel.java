package com.github.abhishekroy666.chatbot.model;

import com.github.abhishekroy666.chatbot.enums.MessageType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Abhishek Roy
 */
@Data
public class SentenceModel {

    private Integer id;

    @NotNull
    private MessageType messageType;

    @NotBlank
    private String text;
}
