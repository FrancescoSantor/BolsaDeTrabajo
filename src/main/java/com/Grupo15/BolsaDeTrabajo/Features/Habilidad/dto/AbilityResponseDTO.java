package com.Grupo15.BolsaDeTrabajo.Features.Habilidad.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class AbilityResponseDTO {
    private UUID externalId;
    private String name;
    private String category;
}