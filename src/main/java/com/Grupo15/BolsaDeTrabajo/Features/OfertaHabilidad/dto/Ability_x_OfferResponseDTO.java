package com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class Ability_x_OfferResponseDTO {
    private UUID externalId;
    private String offerTitle;      // viene de offer.title
    private String offerLocation;   // viene de offer.location
    private String abilityName;     // viene de abilities.name
    private String abilityCategory; // viene de abilities.category
    private boolean required;
}