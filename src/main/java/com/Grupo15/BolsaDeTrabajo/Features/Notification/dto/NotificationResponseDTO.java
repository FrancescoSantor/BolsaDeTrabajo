package com.Grupo15.BolsaDeTrabajo.Features.Notification.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record NotificationResponseDTO(
        UUID externalId,
        String userName,      // viene de user.name
        String message,
        Boolean read,
        Timestamp createdAt
) {
}
