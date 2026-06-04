package com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Builder
@Data
public class Ability_x_OfferResponseDTO {
    private UUID externalId;
    private String offerTitle;      // viene de offer.title
    private String offerLocation;   // viene de offer.location
    private String abilityName;     // viene de abilities.name
    private String abilityCategory; // viene de abilities.category
    private boolean required;
}