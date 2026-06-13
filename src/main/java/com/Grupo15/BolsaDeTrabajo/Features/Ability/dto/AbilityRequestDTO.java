package com.Grupo15.BolsaDeTrabajo.Features.Ability.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AbilityRequestDTO(
        @NotBlank
        String name,
        @NotNull
        AbilityCategory category
) {}
