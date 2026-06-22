package com.Grupo15.BolsaDeTrabajo.Features.Offer.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferType;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;


public record OfferRequestDTO(

        @NotNull(message = "El ID de la empresa es obligatorio.")
        Long companyId,
        @NotNull(message = "El titulo/area es obligatorio.")
        Title title,
        @NotBlank(message = "La descripcion de la oferta no puede estar vacia.")
        @Size(min = 10, max = 2000, message = "La descripcion debe tener entre 10 y 2000 caracteres.")
        String description,

        @NotNull(message = "La modalidad de trabajo es obligatoria.")
        OfferType modality,

        @NotBlank(message = "El tipo de contrato es obligatorio.")
        String contractType,

        @NotNull(message = "El salario minimo es obligatorio.")
        @PositiveOrZero(message = "El salario mminimo no puede ser negativo.")
        Double minSalary,

        @NotNull(message = "El salario maximo es obligatorio.")
        @PositiveOrZero(message = "El salario maximo no puede ser negativo.")
        Double maxSalary,

        @NotNull(message = "La fecha de publicación es obligatoria.")
        Timestamp publicationDate,

        @NotNull(message = "La fecha de cierre es obligatoria.")
        Timestamp publicationClosing,

        String location
) {}