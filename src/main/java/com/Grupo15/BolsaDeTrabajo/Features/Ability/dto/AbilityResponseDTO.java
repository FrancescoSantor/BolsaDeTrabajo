package com.Grupo15.BolsaDeTrabajo.Features.Ability.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityCategory;
import java.util.UUID;


public record AbilityResponseDTO(
        UUID externalId,
        String name,
        AbilityCategory category
) {
}