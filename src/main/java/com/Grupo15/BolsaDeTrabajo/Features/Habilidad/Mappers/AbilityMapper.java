package com.Grupo15.BolsaDeTrabajo.Features.Habilidad.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.AbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.dto.AbilityResponseDTO;

public class AbilityMapper {

    public static AbilityResponseDTO toResponse (AbilityEntity ability) {
        return AbilityResponseDTO.builder()
                .externalId(ability.getExternalId())
                .name(ability.getName())
                .category(ability.getCategory())
                .build();
    }

    public static AbilityEntity toRequest (AbilityRequestDTO request) {
        return AbilityEntity.builder()
                .name(request.name())
                .category(request.category())
                .build();
    }
}
