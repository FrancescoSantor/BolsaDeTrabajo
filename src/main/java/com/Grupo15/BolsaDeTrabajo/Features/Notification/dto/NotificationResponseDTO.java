package com.Grupo15.BolsaDeTrabajo.Features.Notification.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class NotificationResponseDTO {
    private UUID externalId;
    private String userName;      // viene de user.name
    private String userLastName;  // viene de user.lastName
    private String message;
    private boolean read;
    private Timestamp createdAt;
}