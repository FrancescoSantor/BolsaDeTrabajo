package com.Grupo15.BolsaDeTrabajo.Features.Mensajes.dto;

public record MessagesRequestDTO(
        Long issuerId,
        Long receptorId,
        String content
) {}