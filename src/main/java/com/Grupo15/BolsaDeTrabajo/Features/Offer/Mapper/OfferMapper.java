package com.Grupo15.BolsaDeTrabajo.Features.Offer.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    // De Entidad a DTO (Trae el externalId heredado de BaseEntity automáticamente)
    @Mapping(source = "company.name", target = "companyName")
    OfferResponseDTO toDto(OfferEntity entity);

    // De Request a Entidad Nueva
    // Ignoramos el ID numérico y la empresa para manejarlos de forma segura en el Service
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    OfferEntity toEntity(OfferRequestDTO request);

}