package com.github.abhishekroy666.chatbot.model;

import com.github.abhishekroy666.chatbot.enums.SentenceType;
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
    private SentenceType sentenceType;

    @NotBlank
    private String text;
}
