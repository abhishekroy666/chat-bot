package com.github.abhishekroy666.chatbot.model;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Abhishek Roy
 */
@Data
public class ResponseTypeModel {

    private Integer id;

    @NotBlank
    private SentenceType type;

    private String description;
}
