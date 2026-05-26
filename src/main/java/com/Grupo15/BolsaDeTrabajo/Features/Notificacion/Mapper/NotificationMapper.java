package com.Grupo15.BolsaDeTrabajo.Features.Notificacion.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Notificacion.NotificationEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Notificacion.dto.NotificationResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto.PostulationsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto.PostulationsResponseDTO;

public class NotificationMapper {

    public static NotificationResponseDTO toDto(NotificationEntity entity)
    {
        return NotificationResponseDTO.builder()
                .externalId(entity.getExternalId())
                .userName(entity.getUser().getName())
                .userLastName(entity.getUser().getLastName())
                .message(entity.getMessage())
                .read(entity.isRead())
                .createdAt(entity.getCreatedAt())
                .build();
    }
    public static NotificationEntity toEntity(NotificationResponseDTO request)
    {
        return NotificationEntity.builder()
                .message(request.getMessage())
                .read(request.isRead())
                .createdAt(request.getCreatedAt())
                .build();

    }
}
