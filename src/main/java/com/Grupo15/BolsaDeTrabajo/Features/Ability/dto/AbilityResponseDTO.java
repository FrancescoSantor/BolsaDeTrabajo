package com.Grupo15.BolsaDeTrabajo.Features.Ability.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityCategory;
import lombok.Builder;

import java.util.UUID;

@Builder
public class AbilityResponseDTO {
    private UUID externalId;
    private String name;
    private AbilityCategory category;
}