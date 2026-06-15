package com.Grupo15.BolsaDeTrabajo.Features.Message.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Message.MessagesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessagesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessagesResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;

public class MessageMapper {

    public static MessagesResponseDTO toDto(MessagesEntity entity)
    {
        return MessagesResponseDTO.builder()
                .externalId(entity.getExternalId())
                .issuerName(entity.getIssuer().getName())
                .receptorName(entity.getReceptor().getName())
                .content(entity.getContent())
                .read(entity.isRead())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static MessagesEntity toEntity(MessagesRequestDTO request)
    {
        return MessagesEntity.builder()
                .content(request.content())
                .isRead(false)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }
}
