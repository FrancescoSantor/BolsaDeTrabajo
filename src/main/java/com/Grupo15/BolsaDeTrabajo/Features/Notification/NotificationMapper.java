package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "user" , ignore = true)
    @Mapping(target = "IsRead" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public NotificationEntity toEntity(NotificationRequestDTO requestDTO);

    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "IsRead" , source = "isRead")
    public NotificationResponseDTO toDto (NotificationEntity notification);
}
