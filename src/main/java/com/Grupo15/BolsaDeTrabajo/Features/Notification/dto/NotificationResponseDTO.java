package com.Grupo15.BolsaDeTrabajo.Features.Notification.dto;

import lombok.Builder;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
public record NotificationResponseDTO(
        UUID externalId,
        String userName,      // viene de user.name
        String message,
        boolean IsRead,
        Timestamp createdAt
) {
}
