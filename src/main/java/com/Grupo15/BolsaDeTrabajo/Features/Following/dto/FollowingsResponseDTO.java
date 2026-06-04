package com.Grupo15.BolsaDeTrabajo.Features.Following.dto;

import lombok.Builder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class FollowingsResponseDTO {
    private UUID externalId;
    private String userName;          // viene de user.name
    private String companyName;       // viene de company.registeredName
    private LocalDateTime createdAt;
}
