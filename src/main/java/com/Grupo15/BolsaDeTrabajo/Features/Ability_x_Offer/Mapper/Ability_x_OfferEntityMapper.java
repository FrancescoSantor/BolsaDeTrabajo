package com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.Ability_x_OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.dto.Ability_x_OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.dto.Ability_x_OfferResponseDTO;

public class Ability_x_OfferEntityMapper {

    public static Ability_x_OfferResponseDTO toDto(Ability_x_OfferEntity entity)
    {
        return  Ability_x_OfferResponseDTO.builder()
                .externalId(entity.getExternalId())
                .offerTitle(String.valueOf(entity.getOffer().getTitle()))
                .abilityName(entity.getAbilities().getName())
                .abilityCategory(entity.getAbilities().getCategory())
                .required(entity.isRequired())
                .build();
    }

    public static Ability_x_OfferEntity toEntity(Ability_x_OfferRequestDTO request)
    {
        return Ability_x_OfferEntity.builder()
                .required(request.required())
                .build();
    }
}
