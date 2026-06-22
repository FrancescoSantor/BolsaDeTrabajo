package com.Grupo15.BolsaDeTrabajo.Features.Message.dto;

import java.util.UUID;

public record MessageRequestDTO(
        UUID issuerId,
        UUID receptorId,
        String content
) {}