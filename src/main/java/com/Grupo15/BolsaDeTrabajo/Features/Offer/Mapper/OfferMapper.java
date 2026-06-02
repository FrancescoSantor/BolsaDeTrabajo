package com.Grupo15.BolsaDeTrabajo.Features.Offer.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;

public class OfferMapper {

    public static OfferResponseDTO toDto(OfferEntity entity) {
        return OfferResponseDTO.builder()
                .externalId(entity.getExternalId())
                .companyName(entity.getCompany().getName())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .modality(entity.getModality())
                .minSalary(entity.getMinSalary())
                .maxSalary(entity.getMaxSalary())
                .status(entity.getStatus())
                .publicationDate(entity.getPublicationDate())
                .publicationClosing(entity.getPublicationClosing())
                .build();
    }

    public static OfferEntity toEntity(OfferRequestDTO request) {
        return OfferEntity.builder()
                .id(request.companyId())
                .title(request.title())
                .description(request.description())
                .modality(request.modality())
                .contractType(request.contractType())
                .minSalary(request.minSalary())
                .maxSalary(request.maxSalary())
                .status(request.status())
                .publicationDate(request.publicationDate())
                .publicationClosing(request.publicationClosing())
                .build();
    }
}