package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SavedRequestDTO(

        @NotNull(message = "Candidate id is required")
        UUID candidateId,

        @NotNull(message = "Offer id is required")
        Long offerId

) {}