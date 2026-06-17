package com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostulationNewDTO(
        @NotNull
        UUID idCandidate,
        @NotNull
        UUID idOffer,
        @NotBlank()
        String coverLetter
) {
}
