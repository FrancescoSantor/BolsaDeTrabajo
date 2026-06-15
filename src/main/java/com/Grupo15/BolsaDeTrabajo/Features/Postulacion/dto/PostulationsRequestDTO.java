package com.Grupo15.BolsaDeTrabajo.Features.Postulacion.dto;

public record PostulationsRequestDTO(
        Long candidateId,
        Long offerId,
        String coverLetter
) {}
