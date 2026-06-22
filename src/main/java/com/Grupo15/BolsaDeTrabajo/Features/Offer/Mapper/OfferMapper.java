package com.Grupo15.BolsaDeTrabajo.Features.Offer.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(source = "company.name", target = "companyName")
    OfferResponseDTO toDto(OfferEntity entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "applications", ignore = true)
    @Mapping(target = "abilities", ignore = true)
    @Mapping(target = "saved", ignore = true)
    //@Mapping(target = "location", ignore = true)
    OfferEntity toEntity(OfferRequestDTO request);

}