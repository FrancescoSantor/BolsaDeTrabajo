package com.Grupo15.BolsaDeTrabajo.Features.Ability.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface AbilityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ability_x_OfferEntities", ignore = true)
    @Mapping(target = "abilityCandidate", ignore = true)
    AbilityEntity toEntity (AbilityRequestDTO Dto);

    AbilityResponseDTO toDto (AbilityEntity ability);
}
