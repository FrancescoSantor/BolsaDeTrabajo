package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SavedCandidateRequestDto(

        @NotNull(message = "Company external id is required")
        UUID companyExternalId,

        @NotNull(message = "Candidate id is required")
        UUID candidateExternalId
) {}
