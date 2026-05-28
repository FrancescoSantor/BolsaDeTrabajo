package com.Grupo15.BolsaDeTrabajo.Features.Message.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
public class MessagesResponseDTO {
    private UUID externalId;
    private String issuerName;       // viene de issuer.name
    private String issuerLastName;   // viene de issuer.lastName
    private String receptorName;     // viene de receptor.name
    private String receptorLastName; // viene de receptor.lastName
    private String content;
    private boolean read;
    private Timestamp createdAt;
}