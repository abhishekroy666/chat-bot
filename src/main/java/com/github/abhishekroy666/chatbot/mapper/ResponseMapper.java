package com.github.abhishekroy666.chatbot.mapper;

import com.github.abhishekroy666.chatbot.entity.Response;
import com.github.abhishekroy666.chatbot.model.ResponseModel;
import org.mapstruct.Mapper;

/**
 * @author Abhishek Roy
 */
@Mapper(componentModel = "spring", uses = {ResponseTypeMapper.class})
public interface ResponseMapper {

    ResponseModel mapEntityToModel(Response response);

    Response mapModelToEntity(ResponseModel responseModel);
}
