package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaboralExperienceResponseDTO {
    private UUID externalId;
    private String candidateName;
    private String candidateLastName;
    private String company;
    private String position;
    private LocalDate initialDate;
    private LocalDate endDate;
    private String description;
    private String companyReferenceName;
}