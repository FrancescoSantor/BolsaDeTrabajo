package com.Grupo15.BolsaDeTrabajo.Features.Saved.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Saved.SavedEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedCandidateResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedOfferResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface SavedMapper {

    @Mapping(source = "offer.externalId", target = "offerExternalId")
    @Mapping(source = "offer.title", target = "offerTitle")
    @Mapping(source = "offer.location", target = "offerLocation")
    @Mapping(source = "offer.company.name", target = "companyName")
    SavedOfferResponseDTO toOfferDto(SavedEntity entity);

    @Mapping(source = "candidate.externalId", target = "candidateExternalId")
    @Mapping(source = "candidate.name", target = "candidateName")
    @Mapping(source = "candidate.lastName", target = "candidateLastName")
    SavedCandidateResponseDTO toCandidateDto(SavedEntity entity);
}