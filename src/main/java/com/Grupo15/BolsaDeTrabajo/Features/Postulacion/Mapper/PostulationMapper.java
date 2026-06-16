package com.Grupo15.BolsaDeTrabajo.Features.Postulacion.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto.PostulationsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto.PostulationsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto.PostulationsResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;

public class PostulationMapper {

    public static PostulationsResponseDTO toDto(PostulationsEntity entity)
    {
        return PostulationsResponseDTO.builder()
                .externalId(entity.getExternalId())
                .candidateLastName(entity.getCandidate().getName())
                .candidateLastName(entity.getCandidate().getLastName())
                .offerTitle(String.valueOf(entity.getOffer().getTitle()))
                .companyName(entity.getOffer().getCompany().getName())
                .status(String.valueOf(entity.getStatus()))
                .coverLetter(entity.getCoverLetter())
                .postulationDate(entity.getPostulationDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }
    public  static PostulationsEntity toEntity(PostulationsRequestDTO request)
    {
        return PostulationsEntity.builder()
                .coverLetter(request.coverLetter())
                .postulationDate(Timestamp.from(Instant.now()))
                .updateDate(Timestamp.from(Instant.now()))
                .build();
    }
}