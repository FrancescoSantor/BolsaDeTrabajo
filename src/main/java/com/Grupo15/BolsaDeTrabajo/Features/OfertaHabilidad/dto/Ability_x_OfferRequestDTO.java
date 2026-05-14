package com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.dto;

public record Ability_x_OfferRequestDTO(
        Long offerId,
        Long abilityId,
        boolean required
) {}
