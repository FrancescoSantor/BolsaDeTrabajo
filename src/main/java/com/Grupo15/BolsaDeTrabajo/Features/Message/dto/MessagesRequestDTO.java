package com.Grupo15.BolsaDeTrabajo.Features.Message.dto;

public record MessagesRequestDTO(
        Long issuerId,
        Long receptorId,
        String content
) {}