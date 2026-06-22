package com.Grupo15.BolsaDeTrabajo.Features.CandidateAbility.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityCategory;
import lombok.Builder;

import java.util.UUID;

@Builder
public class CandidateAbilityResponseDTO {
    private UUID externalId;          // ← reemplaza el Long id
    private String candidateName;
    private String abilityName;
    private AbilityCategory abilityCategory;
}


