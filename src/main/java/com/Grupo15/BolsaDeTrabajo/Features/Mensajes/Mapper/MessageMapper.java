package com.Grupo15.BolsaDeTrabajo.Features.Mensajes.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Mensajes.MessagesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Mensajes.dto.MessagesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Mensajes.dto.MessagesResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;

public class MessageMapper {

    public static MessagesResponseDTO toDto(MessagesEntity entity)
    {
        return MessagesResponseDTO.builder()
                .externalId(entity.getExternalId())
                .issuerName(entity.getIssuer().getName())
                .issuerLastName(entity.getIssuer().getLastName())
                .receptorName(entity.getReceptor().getName())
                .receptorLastName(entity.getReceptor().getLastName())
                .content(entity.getContent())
                .read(entity.isRead())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static MessagesEntity toEntity(MessagesRequestDTO request)
    {
        return MessagesEntity.builder()
                .content(request.content())
                .read(false)
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }
}
