package com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.LaboralExperienceEntity;
import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.dto.LaboralExperienceResponseDTO;

public class LaboralExperienceMapper {

    public static LaboralExperienceResponseDTO toResponse (
            LaboralExperienceEntity laboralExperience,
            String candidateName,
            String candidateLastName,
            String company,
            String companyReferenceName) {

        return LaboralExperienceResponseDTO.builder()
                .externalId(laboralExperience.getExternalId())
                .candidateName(candidateName)
                .candidateLastName(candidateLastName)
                .company(company)
                .position(laboralExperience.getPosition())
                .initialDate(laboralExperience.getInitialDate())
                .endDate(laboralExperience.getEndDate())
                .currentWork(laboralExperience.isCurrentWork())
                .description(laboralExperience.getDescription())
                .companyReferenceName(companyReferenceName)
                .build();
    }

    public static LaboralExperienceEntity toRequest (LaboralExperienceRequestDTO request) {
        return LaboralExperienceEntity.builder()
                .company(request.company())
                .position(request.position())
                .initialDate(request.initialDate())
                .endDate(request.endDate())
                .currentWork(request.currentWork())
                .description(request.description())
                .build();
    }
}
