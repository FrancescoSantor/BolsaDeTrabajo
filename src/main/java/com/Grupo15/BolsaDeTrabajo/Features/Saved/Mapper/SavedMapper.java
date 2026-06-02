package com.Grupo15.BolsaDeTrabajo.Features.Saved.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Saved.SavedEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;

public class SavedMapper {

    public static SavedResponseDTO toDto(SavedEntity entity)
    {
        return SavedResponseDTO.builder()
                .externalId(entity.getExternalId())
                .candidateName(entity.getCandidate().getName())
                .candidateLastName(entity.getCandidate().getLastName())
                .offerTitle(String.valueOf(entity.getOffer().getTitle()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static SavedEntity toEntity(SavedResponseDTO request)
    {
        return SavedEntity.builder()
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }
}
