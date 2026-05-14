package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.CandidateAbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.dto.CandidateAbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.dto.CandidateAbilityResponseDTO;

public class CandidateAbilityMapper {
    public static CandidateAbilityResponseDTO toResponse (CandidateAbilityEntity candidateAbility) {
        return CandidateAbilityResponseDTO.builder()
                .externalId(candidateAbility.getExternalId())
                .candidateName(candidateAbility.getCandidate().getName())
                .candidateLastName(candidateAbility.getCandidate().getLastName())
                .abilityName(candidateAbility.getAbility().getName())
                .abilityCategory(candidateAbility.getAbility().getCategory())
                .build();
    }

    public static CandidateAbilityEntity toRequest (CandidateAbilityRequestDTO request) {
        return CandidateAbilityEntity.builder()
                .build();
    }
}