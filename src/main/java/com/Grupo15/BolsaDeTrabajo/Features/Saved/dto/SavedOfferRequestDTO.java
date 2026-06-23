package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SavedOfferRequestDTO(

        @NotNull(message = "Candidate id is required")
        UUID candidateExternalId,

        @NotNull(message = "Offer id is required")
        UUID offerExternalId

) {}