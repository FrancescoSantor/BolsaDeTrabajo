package com.Grupo15.BolsaDeTrabajo.Features.Ability.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityResponseDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface AbilityMapper {

    AbilityEntity toEntity (AbilityRequestDTO Dto);
    AbilityResponseDTO toDto (AbilityEntity ability);
}
