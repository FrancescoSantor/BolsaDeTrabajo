package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferType;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferStatus;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
public class OfferResponseDTO {
    private UUID externalId;
    private String companyName;       // viene de company.registeredName
    private String companyLocation;   // viene de company.location
    private Title title;
    private String description;
    private String location;
    private OfferType modality;
    private String contractType;
    private Double minSalary;
    private Double maxSalary;
    private OfferStatus status;
    private Timestamp publicationDate;
    private Timestamp publicationClosing;
}