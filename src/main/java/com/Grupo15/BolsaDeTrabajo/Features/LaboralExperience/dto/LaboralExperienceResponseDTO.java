package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import lombok.Builder;

import java.sql.Date;
import java.util.UUID;

@Builder
public class LaboralExperienceResponseDTO {
    private UUID externalId;
    private String candidateName;        // viene de candidate.user.name
    private String candidateLastName;    // viene de candidate.user.lastName
    private String company;              // viene de LaboralExperienceEntity.company
    private String position;
    private Date initialDate;
    private Date endDate;
    private boolean currentWork;
    private String description;
    private String companyReferenceName; // viene de companyReferences.registeredName
}