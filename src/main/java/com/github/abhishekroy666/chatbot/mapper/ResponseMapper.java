package com.github.abhishekroy666.chatbot.mapper;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author Abhishek Roy
 */
@Mapper(componentModel = "spring", uses = {ResponseTypeMapper.class})
public interface ResponseMapper {

    ResponseModel mapEntityToModel(Response response);

    Response mapModelToEntity(ResponseModel responseModel);

    void update(@MappingTarget Response response, ResponseModel responseModel);
}
