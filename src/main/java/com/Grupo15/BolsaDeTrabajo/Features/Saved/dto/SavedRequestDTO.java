package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import jakarta.validation.constraints.NotNull;

public record SavedRequestDTO(

        @NotNull(message = "Candidate id is required")
        Long candidateId,

        @NotNull(message = "Offer id is required")
        Long offerId

) {}