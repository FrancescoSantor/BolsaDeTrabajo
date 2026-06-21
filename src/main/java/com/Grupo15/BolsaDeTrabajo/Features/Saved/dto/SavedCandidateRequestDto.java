package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import java.util.UUID;

public record SavedCandidateRequestDto(
        Long companyId,
        UUID candidateId
) {
}
