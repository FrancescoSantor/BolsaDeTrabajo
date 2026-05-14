package com.Grupo15.BolsaDeTrabajo.Features.Habilidad.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Builder
public class AbilityResponseDTO {
    private UUID externalId;
    private String name;
    private String category;
}