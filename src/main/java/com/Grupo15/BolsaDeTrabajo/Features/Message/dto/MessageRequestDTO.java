package com.Grupo15.BolsaDeTrabajo.Features.Message.dto;

public record MessageRequestDTO(
        Long issuerId,
        Long receptorId,
        String content
) {}