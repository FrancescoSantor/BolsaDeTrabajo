package com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto;

import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
public class PostulationsResponseDTO {
    private UUID externalId;
    private String candidateName;     // viene de candidate.user.name
    private String candidateLastName; // viene de candidate.user.lastName
    private String offerTitle;        // viene de offer.title
    private String companyName;       // viene de offer.company.registeredName
    private String status;
    private String coverLetter;
    private Timestamp postulationDate;
    private Timestamp updateDate;
}