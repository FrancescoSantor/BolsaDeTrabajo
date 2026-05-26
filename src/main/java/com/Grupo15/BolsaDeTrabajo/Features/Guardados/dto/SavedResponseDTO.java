package com.Grupo15.BolsaDeTrabajo.Features.Guardados.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class SavedResponseDTO {
    private UUID externalId;
    private String candidateName;     // viene de candidate.user.name
    private String candidateLastName; // viene de candidate.user.lastName
    private String offerTitle;        // viene de offer.title
    private String offerLocation;     // viene de offer.location
    private Timestamp createdAt;
}