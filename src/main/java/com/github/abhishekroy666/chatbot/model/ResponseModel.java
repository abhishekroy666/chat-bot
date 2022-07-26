package com.github.abhishekroy666.chatbot.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Abhishek Roy
 */
@Data
public class ResponseModel {

    private Integer id;

    @NotNull
    private ResponseTypeModel responseType;

    @NotBlank
    private String text;
}
