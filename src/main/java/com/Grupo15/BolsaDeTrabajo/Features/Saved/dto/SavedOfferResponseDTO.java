package com.Grupo15.BolsaDeTrabajo.Features.Saved.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record SavedOfferResponseDTO (
     UUID externalId,
     UUID offerExternalId,
     String offerTitle,
     String offerLocation,
     String companyName,
     Timestamp createdAt)
{}