package com.Grupo15.BolsaDeTrabajo.Features.Offer.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.Type;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.Status;
import java.sql.Timestamp;

public record OfferRequestDTO(
        Long companyId,
        Title title,
        String description,
        Type modality,
        String contractType,
        Double minSalary,
        Double maxSalary,
        Status status,
        Timestamp publicationDate,
        Timestamp publicationClosing
) {}