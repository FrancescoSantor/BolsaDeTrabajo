package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OfferService {

    OfferResponseDTO createOffer(OfferRequestDTO requestDto);

    OfferResponseDTO updateOffer(UUID externalId, OfferRequestDTO requestDto);

    void deleteOffer(UUID externalId);

    OfferResponseDTO getOfferById(UUID externalId);

    Page<OfferResponseDTO> getOffers(Pageable pageable, TitleOfOffer titleOfOfferEnum);

}
