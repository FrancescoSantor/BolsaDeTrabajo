package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferType;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferStatus;
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