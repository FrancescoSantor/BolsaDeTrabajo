package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;

import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceResponseDTO;

import java.util.List;
import java.util.UUID;

public interface LaboralExperienceService {


    LaboralExperienceResponseDTO createExperience(LaboralExperienceRequestDTO requestDto);

    LaboralExperienceResponseDTO updateExperience(UUID externalId, LaboralExperienceRequestDTO requestDto);

    void deleteExperience(UUID externalId);

    LaboralExperienceResponseDTO getExperienceByExternalId(UUID externalId);

    List<LaboralExperienceResponseDTO> getExperiencesByCandidate(Long candidateId);
}
