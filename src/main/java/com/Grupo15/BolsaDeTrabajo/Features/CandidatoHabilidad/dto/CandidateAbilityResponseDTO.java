package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class CandidateAbilityResponseDTO {
    private UUID externalId;          // ← reemplaza el Long id
    private String candidateName;
    private String candidateLastName;
    private String abilityName;
    private String abilityCategory;
}


