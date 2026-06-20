package com.Grupo15.BolsaDeTrabajo.Features.Notification.dto;

import java.util.UUID;

public record NotificationRequestDTO(
        UUID userId,
        String message
) {}
