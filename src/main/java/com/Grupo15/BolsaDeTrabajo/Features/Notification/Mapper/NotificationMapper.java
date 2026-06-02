package com.Grupo15.BolsaDeTrabajo.Features.Notification.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Notification.NotificationEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;

public class NotificationMapper {

    public static NotificationResponseDTO toDto(NotificationEntity entity)
    {
        return NotificationResponseDTO.builder()
                .externalId(entity.getExternalId())
                .userName(entity.getUser().getName())
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
