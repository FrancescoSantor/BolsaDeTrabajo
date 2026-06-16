package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    public NotificationEntity toEntity(NotificationRequestDTO requestDTO);

    public NotificationResponseDTO toDto (NotificationEntity notification);
}
