package com.Grupo15.BolsaDeTrabajo.Features.Message.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Message.MessageEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface MessageMapper {

    MessageEntity toEntity (MessageRequestDTO messageRequestDTO);
    MessageResponseDTO toDto (MessageEntity messageEntity);

}
