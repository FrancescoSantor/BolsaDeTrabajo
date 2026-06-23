package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.LaboralExperienceEntity;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LaboralExperienceMapper {

    @Mapping(source = "candidate.name", target = "candidateName")
    @Mapping(source = "candidate.lastName", target = "candidateLastName")
    LaboralExperienceResponseDTO toDto(LaboralExperienceEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "companyReferences", ignore = true)
    LaboralExperienceEntity toEntity(LaboralExperienceRequestDTO request);
}