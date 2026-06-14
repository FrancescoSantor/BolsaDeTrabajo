package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.LaboralExperienceEntity;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LaboralExperienceMapper {

    // Navegamos directo desde la relación 'candidate' (que es un CandidatesEntity)
    @Mapping(source = "candidate.name", target = "candidateName")
    @Mapping(source = "candidate.lastName", target = "candidateLastName")
    @Mapping(source = "company", target = "company")
    // Si no usamos referencias de empresas en el request/entity, este mapping ignorará el campo o podemos dejarlo según la entidad
    @Mapping(source = "companyReference.registeredName", target = "companyReferenceName")
    LaboralExperienceResponseDTO toDto(LaboralExperienceEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "companyReference", ignore = true)
    LaboralExperienceEntity toEntity(LaboralExperienceRequestDTO request);
}