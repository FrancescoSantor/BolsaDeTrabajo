package com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.dto;

import lombok.Data;
import java.sql.Date;
import java.util.UUID;

@Data
public class ExperienceLaboralResponseDTO {
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