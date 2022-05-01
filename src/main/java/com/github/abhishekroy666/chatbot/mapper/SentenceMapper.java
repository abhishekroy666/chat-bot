package com.github.abhishekroy666.chatbot.mapper;

import com.github.abhishekroy666.chatbot.entity.Sentence;
import com.github.abhishekroy666.chatbot.model.SentenceModel;
import org.mapstruct.Mapper;

/**
 * @author Abhishek Roy
 */
@Mapper(componentModel = "spring")
public interface SentenceMapper {

    SentenceModel mapEntityToModel(Sentence sentence);

    Sentence mapModelToEntity(SentenceModel sentenceModel);
}
