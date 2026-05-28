package com.Grupo15.BolsaDeTrabajo.Features.Offer.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferType;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import java.sql.Timestamp;

public record OfferRequestDTO(
        Long companyId,
        Title title,
        String description,
        String location,
        OfferType modality,
        String contractType,
        Double minSalary,
        Double maxSalary,
        OfferStatus status,
        Timestamp publicationDate,
        Timestamp publicationClosing
) {}