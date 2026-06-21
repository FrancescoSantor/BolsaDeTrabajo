package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface OfferService {

    OfferResponseDTO createOffer(OfferRequestDTO requestDto);

    OfferResponseDTO updateOffer(UUID externalId, OfferRequestDTO requestDto, Authentication authentication);

    void deleteOffer(UUID externalId, Authentication authentication);

    OfferResponseDTO getOfferById(UUID externalId);

    Page<OfferResponseDTO> getOffers(Pageable pageable, TitleOfOffer titleOfOfferEnum);


}
