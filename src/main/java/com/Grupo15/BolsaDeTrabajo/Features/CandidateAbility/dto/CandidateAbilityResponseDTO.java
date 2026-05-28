package com.Grupo15.BolsaDeTrabajo.Features.CandidateAbility.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class CandidateAbilityResponseDTO {
    private UUID externalId;          // ← reemplaza el Long id
    private String candidateName;
    private String candidateLastName;
    private String abilityName;
    private String abilityCategory;
}


