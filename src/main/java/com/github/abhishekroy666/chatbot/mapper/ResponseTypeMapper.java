package com.github.abhishekroy666.chatbot.mapper;

import com.github.abhishekroy666.chatbot.entity.ResponseType;
import com.github.abhishekroy666.chatbot.model.ResponseTypeModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author Abhishek Roy
 */
@Mapper(componentModel = "spring")
public interface ResponseTypeMapper {

    ResponseTypeModel mapEntityToModel(ResponseType responseType);

    ResponseType mapModelToEntity(ResponseTypeModel responseTypeModel);

    void update(@MappingTarget ResponseType entity, ResponseTypeModel responseTypeModel);
}
