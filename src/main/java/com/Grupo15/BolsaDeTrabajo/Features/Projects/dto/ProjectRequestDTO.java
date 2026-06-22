package com.Grupo15.BolsaDeTrabajo.Features.Projects.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.UUID;

public record ProjectRequestDTO(
        @NotNull(message = "El ID deL candidato es obligatorio.")
        UUID candidateId,

        @NotBlank(message = "El titulo del proyecto no puede estar vacio.")
        @Size(min = 10, max = 200, message = "El titulo debe tener entre 10 y 200 caracteres.")
        String projectName,

        @NotBlank(message = "La descripcion del proyecto no puede estar vacia.")
        @Size(min = 10, max = 2000, message = "La descripcion debe tener entre 10 y 2000 caracteres.")
        String description,

        @NotNull(message = "La fecha de inicio es obligatoria.")
        Timestamp initialDate,

        @NotNull(message = "La fecha de cierre es obligatoria.")
        Timestamp endDate,

        String urlLink
) {
}
