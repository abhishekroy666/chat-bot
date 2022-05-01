package com.github.abhishekroy666.chatbot.model;

import com.github.abhishekroy666.chatbot.enums.MessageType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Abhishek Roy
 */
@Data
public class ResponseModel {

    private Integer id;

    @NotNull(message = "messageType is mandatory")
    private MessageType messageType;

    @NotBlank(message = "text is mandatory")
    private String text;
}
