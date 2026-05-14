package com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
public class FollowingsResponseDTO {
    private UUID externalId;
    private String userName;          // viene de user.name
    private String userLastName;      // viene de user.lastName
    private String companyName;       // viene de company.registeredName
    private String companyLocation;   // viene de company.location
    private Timestamp createdAt;
}
