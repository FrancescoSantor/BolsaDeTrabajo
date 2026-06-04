package com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.dto;

public record Ability_x_OfferRequestDTO(
        Long offerId,
        Long abilityId,
        boolean required
) {}
