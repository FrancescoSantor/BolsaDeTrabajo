package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public class LaboralExperienceResponseDTO {
    private UUID externalId;
    private String candidateName;        // viene de candidate.user.name
    private String candidateLastName;    // viene de candidate.user.lastName
    private String company;              // viene de LaboralExperienceEntity.company
    private String position;
    private LocalDate initialDate;
    private LocalDate endDate;
    private String description;
    private String companyReferenceName; // viene de companyReferences.registeredName
}